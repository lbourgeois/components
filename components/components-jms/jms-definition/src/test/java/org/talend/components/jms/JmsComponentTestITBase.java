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

package org.talend.components.jms;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.talend.components.api.service.ComponentService;
import org.talend.components.jms.input.JmsInputDefinition;
import org.talend.components.jms.output.JmsOutputDefinition;

public abstract class JmsComponentTestITBase {

    @Inject
    ComponentService componentService;

    public ComponentService getComponentService() {
        return componentService;
    }

    @Test
    public void assertComponentsAreRegistered() {
        assertNotNull(getComponentService().getComponentDefinition(JmsInputDefinition.COMPONENT_NAME));
        assertNotNull(getComponentService().getComponentDefinition(JmsOutputDefinition.COMPONENT_NAME));
    }
}
