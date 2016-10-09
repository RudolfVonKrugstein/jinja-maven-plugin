package de.wintercloud;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import com.hubspot.jinjava.Jinjava;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Goal which renders a jinja file
 */
@Mojo( name = "renderjinja" )
public class CompileJinjaMojo
    extends AbstractMojo
{
    /**
     * Location of the file.
     */
    @Parameter( property = "templatefile", required = true )
    private File templateFile;
    @Parameter( property = "variablesfile", required = true )
    private File varFile;
    @Parameter( property = "outputfile", required = true )
    private File outputFile;

    public void execute()
        throws MojoExecutionException
    {
        try {
            // Load the parameters
            Yaml yaml = new Yaml();
            Map<String, Object> context = (Map<String, Object>) yaml.load(FileUtils.readFileToString(varFile));

            // Load template
            Jinjava jinjava = new Jinjava();
            String template = FileUtils.readFileToString(templateFile);

            // Render and save
            String rendered = jinjava.render(template, context);
            FileUtils.writeStringToFile(outputFile, rendered);
        } catch(IOException e) {
            // Print error and exit with -1
            throw new MojoExecutionException(e.getLocalizedMessage(), e);
        }
    }
}
