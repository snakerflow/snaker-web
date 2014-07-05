/* Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.snaker.modules.flow.ext;

import java.util.List;

import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Task;
import org.snaker.engine.model.WorkModel;

/**
 * @author yuqs
 * @since 1.0
 */
public class CCInterceptor implements SnakerInterceptor {
	public void intercept(Execution execution) {
		List<Task> tasks = execution.getTasks();
		for(Task task : tasks) {
			WorkModel model = task.getModel();
			System.out.println("CCInterceptor====" + model.getClass().getName());
			if(model instanceof ExtTaskModel) {
				ExtTaskModel extModel = (ExtTaskModel)model;
				System.out.println("CCInterceptor====" + extModel.getCcperson());
			}
		}
	}
}
