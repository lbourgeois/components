// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.components.processing.runtime.filterrow;

import java.util.Set;

import org.apache.avro.generic.IndexedRecord;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.apache.beam.sdk.values.TupleTag;
import org.apache.beam.sdk.values.TupleTagList;
import org.apache.commons.lang3.StringUtils;
import org.talend.components.adapter.beam.BeamJobBuilder;
import org.talend.components.adapter.beam.BeamJobContext;
import org.talend.components.api.component.Connector;
import org.talend.components.api.component.runtime.RuntimableRuntime;
import org.talend.components.api.constraint.ElementConstraints;
import org.talend.components.api.container.RuntimeContainer;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.processing.filterrow.FilterRowProperties;
import org.talend.daikon.properties.ValidationResult;

public class FilterRowRuntime extends PTransform<PCollection<Object>, PCollectionTuple>
        implements BeamJobBuilder, RuntimableRuntime<FilterRowProperties> {

    final static TupleTag<IndexedRecord> flowOutput = new TupleTag<IndexedRecord>() {
    };

    final static TupleTag<IndexedRecord> rejectOutput = new TupleTag<IndexedRecord>() {
    };

    final static TupleTag<IndexedRecord> discardOutput = new TupleTag<IndexedRecord>() {
    };

    private FilterRowProperties properties;

    private ElementConstraints inputConstraints;

    private boolean hasFlow;

    private boolean hasReject;

    private boolean stopPipelineOnError;

    @Override
    public ValidationResult initialize(RuntimeContainer container, FilterRowProperties componentProperties) {
        this.properties = componentProperties;
        return ValidationResult.OK;
    }

    @Override
    public PCollectionTuple expand(PCollection<Object> inputPCollection) {

        FilterRowDoFn doFn = new FilterRowDoFn() //
                .withProperties(properties) //
                .withOutputSchema(hasFlow) //
                .withRejectSchema(hasReject)//
                .withInputContraints(inputConstraints)//
                .withStopPipelineOnError(stopPipelineOnError);//

        return inputPCollection.apply(properties.getName(),
                ParDo.withOutputTags(flowOutput, TupleTagList.of(rejectOutput).and(discardOutput)).of(doFn));
    }

    @Override
    public void build(BeamJobContext ctx) {
        String mainLink = ctx.getLinkNameByPortName("input_" + properties.MAIN_CONNECTOR.getName());
        if (!StringUtils.isEmpty(mainLink)) {
            PCollection<Object> mainPCollection = ctx.getPCollectionByLinkName(mainLink);
            this.stopPipelineOnError = properties.stopOnError.getValue();
            if (mainPCollection != null) {
                String flowLink = ctx.getLinkNameByPortName("output_" + properties.FLOW_CONNECTOR.getName());
                String rejectLink = ctx.getLinkNameByPortName("output_" + properties.REJECT_CONNECTOR.getName());

                hasFlow = !StringUtils.isEmpty(flowLink);
                hasReject = !StringUtils.isEmpty(rejectLink);

                PCollectionTuple outputTuples = expand(mainPCollection);

                if (hasFlow) {
                    ctx.putPCollectionByLinkName(flowLink, outputTuples.get(flowOutput));
                }
                if (hasReject) {
                    ctx.putPCollectionByLinkName(rejectLink, outputTuples.get(rejectOutput));
                }
                if (!this.stopPipelineOnError) {
                    ctx.putPCollectionByLinkName(Connector.DISCARD_NAME, outputTuples.get(discardOutput));
                }
                
                // Get input constraints from main input connector
                this.inputConstraints = properties.getElementConstraints(properties.MAIN_CONNECTOR, false);
            }
        }
    }

    /**
     * TODO move this method to a higher level
     * 
     * @param properties
     * @return
     */
    public static ElementConstraints getMainInputConnectorConstraints(ComponentProperties properties) {
        Set<? extends Connector> inputConnectors = properties.getPossibleConnectors(false);

        if (inputConnectors == null) {
            return null;
        }

        for (Connector connector : inputConnectors) {
            if (Connector.MAIN_NAME.equals(connector.getName())) {
                return properties.getElementConstraints(connector, false);
            }
        }

        return null;
    }
}