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
package com.researchspace.dataverse.entities.facade;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Dataset time bounds for 'timePeriodCovered' field.
 * @author ltromel
 */
@Data
@Builder
public class DatasetTimePeriodCovered {

    private @NonNull Date timePeriodCoveredStart, timePeriodCoveredEnd;

}
