// **********************************************************************
//
// Copyright (c) 2014-2017 ZeroC, Inc. All rights reserved.
//
// **********************************************************************

package com.zeroc.gradle.icebuilder.slice

import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Before

import static org.junit.Assume.assumeNoException
import static org.junit.Assume.assumeNotNull

class TestCase {
    def project = null

    @Before
    void createProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'java'
        project.pluginManager.apply 'slice'
    }

    @Before
    void checkIceInstalled() {
    //     try {
            assumeNotNull(project.slice.iceHome)
            assumeNotNull(project.slice.slice2java)
    //     } catch (org.gradle.api.GradleException e) {
    //         assumeNoException(e);
    //     }
    }

    @After
    public void cleanupProject() {
        project.delete()
        project = null
    }

    void forceReinitialization() {
        // setting any variable forces reinitialization
        def iceHome = project.slice.iceHome
        project.slice.iceHome = iceHome
    }

    File pathToFile(pathList) {
        return new File(pathList.join(File.separator))
    }
}
