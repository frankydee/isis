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

package org.apache.isis.core.metamodel.facets;

import java.util.List;

import org.apache.isis.core.metamodel.facetapi.FeatureType;
import org.apache.isis.core.metamodel.services.ServicesInjector;
import org.apache.isis.core.metamodel.services.persistsession.PersistenceSessionServiceInternal;
import org.apache.isis.core.metamodel.specloader.collectiontyperegistry.CollectionTypeRegistry;

public abstract class PropertyOrCollectionIdentifyingFacetFactoryAbstract
        extends MethodPrefixBasedFacetFactoryAbstract
        implements PropertyOrCollectionIdentifyingFacetFactory {


    public PropertyOrCollectionIdentifyingFacetFactoryAbstract(final List<FeatureType> featureTypes, final String... prefixes) {
        super(featureTypes, OrphanValidation.DONT_VALIDATE, prefixes);
    }


    // //////////////////////////////////////

    private final CollectionTypeRegistry collectionTypeRegistry = new CollectionTypeRegistry();

    protected boolean isCollectionOrArray(final Class<?> cls) {
        return getCollectionTypeRepository().isCollectionType(cls) || getCollectionTypeRepository().isArrayType(cls);
    }

    protected CollectionTypeRegistry getCollectionTypeRepository() {
        return collectionTypeRegistry;
    }

    // //////////////////////////////////////



    @Override
    public void setServicesInjector(final ServicesInjector servicesInjector) {
        super.setServicesInjector(servicesInjector);
        adapterManager = servicesInjector.lookupService(PersistenceSessionServiceInternal.class);
    }

    PersistenceSessionServiceInternal adapterManager;



}
