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
</pre>
 */
package com.researchspace.dataverse.http;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.researchspace.dataverse.entities.DataverseContacts;
import com.researchspace.dataverse.entities.DataverseGet;
import com.researchspace.dataverse.entities.DataversePost;
import com.researchspace.dataverse.entities.DataverseResponse;
import com.researchspace.dataverse.entities.DvMessage;

/**
 * Dataverse operations tests.
 */
public class DataverseOperationsTest extends AbstractIntegrationTest {



    @Before
    public void setup() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createPublishAndDeleteNewDataverse(){
        final String dvName = RandomStringUtils.randomAlphabetic(10);
        final DataversePost dv = createADataverse(dvName);
        final DataverseResponse<DataversePost>  success = dataverseOps.createNewDataverse(dataverseAlias, dv);
        assertNotNull(success.getData());
        assertNotNull(success.getData().getId());

        dataverseOps.publishDataverse(dvName);

        final DataverseResponse<DvMessage> deleted = dataverseOps.deleteDataverse(dvName);
        assertTrue(deleted.getStatus().equals("OK"));
        assertNotNull(deleted.getData());
    }

    static DataversePost createADataverse(final String dvName) {
        final DataversePost dv = new DataversePost();
        dv.setAlias(dvName);
        dv.setName("Test Instance " + dvName);
        dv.setDataverseContacts(Arrays.asList(new DataverseContacts("a@b.com")));
        return dv;
    }

    @Test
    public void deleteUnknownDataverseHandled () {
        final DataverseResponse<DvMessage> deleted = dataverseOps.deleteDataverse("ra");
        assertTrue(deleted.getStatus().equals(ERROR_MSG));
        assertNull(deleted.getData());
    }

    @Test(expected=IllegalArgumentException.class)
    public void createDataverseValidation () {
        final String dvName = RandomStringUtils.randomAlphabetic(10);
        final DataversePost dv = createADataverse(dvName);
        dv.setAlias("");
        dataverseOps.createNewDataverse("rspace", dv);
    }
    @Test(expected=IllegalArgumentException.class)
    public void createDataverseValidationContactREquired () {
        final String dvName = RandomStringUtils.randomAlphabetic(10);
        final DataversePost dv = createADataverse(dvName);
        dv.setDataverseContacts(null);
        dataverseOps.createNewDataverse("rspace", dv);
    }

    @Test
    public void testGetDataverseById() {
        final DataverseGet dv = dataverseOps.getDataverseById(dataverseAlias);
        assertNotNull(dv.getId());
        assertTrue(dv.getContactEmails().size() > 0);
    }
}
