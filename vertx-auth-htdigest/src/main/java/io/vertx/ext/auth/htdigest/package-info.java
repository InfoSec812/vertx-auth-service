/*
 * Copyright 2016 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

/**
 * == .htdigest Auth Provider implementation
 *
 * We provide an implementation of {@link io.vertx.ext.auth.AuthProvider} which uses the .digest file format
 * to perform authentication.
 *
 * To use this project, add the following
 * dependency to the _dependencies_ section of your build descriptor:
 *
 * * Maven (in your `pom.xml`):
 *
 * [source,xml,subs="+attributes"]
 * ----
 * <dependency>
 *   <groupId>${maven.groupId}</groupId>
 *   <artifactId>${maven.artifactId}</artifactId>
 *   <version>${maven.version}</version>
 * </dependency>
 * ----
 *
 * * Gradle (in your `build.gradle` file):
 *
 * [source,groovy,subs="+attributes"]
 * ----
 * compile '${maven.groupId}:${maven.artifactId}:${maven.version}'
 * ----
 *
 * To create an instance you first need an .htdigest file. This file is created using the apache htdigest tool.
 *
 * Once you've got one of these you can create a {@link io.vertx.ext.auth.htdigest.HtdigestAuth} instance as follows:
 *
 * [source,$lang]
 * ----
 * {@link examples.AuthHtdigestExamples#example1(io.vertx.core.Vertx)}
 * ----
 *
 * Once you've got your instance you can authenticate with it just like any {@link io.vertx.ext.auth.AuthProvider}.
 *
 * The out of the box config assumes the usage of the file .htdigest in the root of the project.
 *
 * == Authentication
 *
 * When authenticating using this implementation, it assumes that the digest authorization header is parsed as a JSON
 * object which we refer from now on as authentication info:
 *
 * [source,$lang]
 * ----
 * {@link examples.AuthHtdigestExamples#example2(HtdigestAuth)}
 * ----
 *
 * == Provider internal behavior
 *
 * The provider will load the specified .htdigest file at start time and will not watch for modifications. If you
 * require dynamic reloads, you will need to restart the provider.
 *
 * The implementation is does not have any other state than the digest file itself, this means that validation and
 * generation of `nonce` strings and counters must be handled outside this provider.
 *
 * Finally `auth-int` `qop` is not supported to avoid having to consume potential large blobs of data in order to
 * validate the hash of the full request. This is usually also not present on modern web browsers.
 *
 * If validating if a user has a particular permission it will always return false since the htdigest file is a pure
 * authentication mechanism and not authorization.
 */
@Document(fileName = "index.adoc")
@ModuleGen(name = "vertx-auth-htdigest", groupPackage = "io.vertx")
package io.vertx.ext.auth.htdigest;

import io.vertx.codegen.annotations.ModuleGen;
import io.vertx.docgen.Document;
