/** <pre>
Copyright 2016 ResearchSpace

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
</pre> */
package com.researchspace.dataverse.testutils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class TestFileUtils {

    public static String getJsonFromFile (final String filename) {
        try {
            return FileUtils.readFileToString(new File("src/test/resources/data/json", filename));
        } catch (final IOException e) {
            throw new IllegalStateException("Couldn't read file " + filename);
        }
    }

}
