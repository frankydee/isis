/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.isis.viewer.restful.viewer.resources.objects.collections;

import java.text.MessageFormat;

import org.apache.isis.core.commons.authentication.AuthenticationSession;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.spec.feature.OneToManyAssociation;
import org.apache.isis.viewer.restful.viewer.html.HtmlClass;
import org.apache.isis.viewer.restful.viewer.resources.objects.TableColumnNakedObjectAssociation;
import org.apache.isis.viewer.restful.viewer.tree.Element;
import org.apache.isis.viewer.restful.viewer.xom.ResourceContext;

public final class TableColumnOneToManyAssociationAccess extends
    TableColumnNakedObjectAssociation<OneToManyAssociation> {

    public TableColumnOneToManyAssociationAccess(final AuthenticationSession session, final ObjectAdapter nakedObject,
        final ResourceContext resourceContext) {
        super("Access", session, nakedObject, resourceContext);
    }

    @Override
    public Element doTd(final OneToManyAssociation collection) {
        if (!collection.isVisible(getSession(), getNakedObject()).isAllowed()) {
            return xhtmlRenderer.p(null, HtmlClass.PROPERTY);
        }

        final String contextPath = resourceContext.getHttpServletRequest().getContextPath();
        final String collectionId = collection.getId();
        final String uri =
            MessageFormat.format("{0}/object/{1}/collection/{2}", contextPath, getOidStr(), collectionId);
        return xhtmlRenderer.aHref(uri, collectionId, "collection", "nakedObject", HtmlClass.COLLECTION);
    }

}
