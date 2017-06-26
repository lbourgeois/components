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
package org.talend.components.marketo.wizard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.talend.components.marketo.wizard.MarketoComponentWizardBaseProperties.CustomObjectAction;
import org.talend.components.marketo.wizard.MarketoComponentWizardBaseProperties.CustomObjectSyncAction;

public class MarketoComponentWizardBasePropertiesTest {

    MarketoComponentWizardBaseProperties props;

    @Before
    public void setUp() throws Exception {
        props = new MarketoComponentWizardBaseProperties("tests");
    }

    @Test
    public void testGetAllSchemaPropertiesConnectors() throws Exception {
        assertNull(props.getAllSchemaPropertiesConnectors(true));
        assertNull(props.getAllSchemaPropertiesConnectors(false));
    }

    @Test
    public void testEnums() throws Exception {
        assertEquals(CustomObjectAction.describe, CustomObjectAction.valueOf("describe"));
        assertEquals(CustomObjectAction.get, CustomObjectAction.valueOf("get"));
        assertEquals(CustomObjectAction.list, CustomObjectAction.valueOf("list"));
        assertEquals(CustomObjectSyncAction.createOnly, CustomObjectSyncAction.valueOf("createOnly"));
        assertEquals(CustomObjectSyncAction.createOrUpdate, CustomObjectSyncAction.valueOf("createOrUpdate"));
        assertEquals(CustomObjectSyncAction.updateOnly, CustomObjectSyncAction.valueOf("updateOnly"));
    }

    @Test
    public void testBeforeFormPresentFetchLeadSchema() throws Exception {
        // TODO add test after TDI-38416
    }

    @Test
    public void testAfterFetchLeadSchema() throws Exception {
        // TODO add test after TDI-38416
    }
}
