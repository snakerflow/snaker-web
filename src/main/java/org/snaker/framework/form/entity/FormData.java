/*
 *  Copyright 2013-2014 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.snaker.framework.form.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 表单数据
 * @author yuqs
 * @since 1.0
 */
public class FormData {
    //流程定义id
    private String processId;
    //流程定义name
    private String processName;
    //流程实例id
    private String orderId;
    //任务id
    private String taskId;
    //表单实体对象
    private Form form;
    //表单校验信息
    private StringBuilder error;
    //页面提交的表单数据
    private Map<DbTable, Map<String, String>> fieldData = new HashMap<DbTable, Map<String, String>>();
    //页面提交的子表数据
    private Map<DbTable, Map<String, String[]>> subFieldData = new HashMap<DbTable, Map<String, String[]>>();
    public FormData(Form form) {
        this.form = form;
    }
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Map<DbTable, Map<String, String>> getFieldData() {
        return fieldData;
    }

    public void setFieldData(Map<DbTable, Map<String, String>> fieldData) {
        this.fieldData = fieldData;
    }

    public void addFieldData(DbTable key, Map<String, String> value) {
        this.fieldData.put(key, value);
    }
    public Map<DbTable, Map<String, String[]>> getSubFieldData() {
        return subFieldData;
    }
    public void setSubFieldData(Map<DbTable, Map<String, String[]>> subFieldData) {
        this.subFieldData = subFieldData;
    }
    public void addSubData(DbTable key, Map<String, String[]> values) {
        this.subFieldData.put(key, values);
    }

    public String getError() {
        return error == null ? "" : error.toString();
    }

    public void addError(String err) {
        if(this.error == null) this.error = new StringBuilder();
        this.error.append(err).append(",");
    }
}
