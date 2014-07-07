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

/**
 * Created with JetBrains PhpStorm.
 * User: xuheng
 * Date: 12-8-8
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
var templates = [
    {
        "pre":"pre0.png",
        'title':lang.blog,
        'preHtml':'<h1 label="Title center" name="tc" style="border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;text-align:center;margin:0px 0px 20px;"><span style="color:#c0504d;">模板一</span></h1><p style="text-align:center;"><strong class=" ">单列布局模板</strong></p><p style="text-indent:2em;">单列布局模板适用于业务字段较少的表单布局(如常见的用户注册界面)，其布局一般为[字段标题:字段控件]</p><br />',
        "html":'<h1 class="ue_t" label="Title center" name="tc" style="border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;text-align:center;margin:0px 0px 20px;"><span style="font-size: 24px">标题</span><br></h1><table width="100%"><tr><td width="30%" align="right">用户名称：</td><td width="70%"></td></tr><tr><td width="30%" align="right">联系电话：</td><td width="70%"></td></tr></table><p class="ue_t"><br /></p>'
    },
    {
        "pre":"pre1.gif",
        'title':lang.blog,
        'preHtml':'<h1 label="Title center" name="tc" style="border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;text-align:center;margin:0px 0px 20px;"><span style="color:#c0504d;">模板二</span></h1><p style="text-align:center;"><strong class=" ">两列布局模板</strong></p><p style="text-indent:2em;">两列布局模板适用于常见的表单布局，其布局一般为[字段标题:字段控件|字段标题:字段控件]</p><br />',
        "html":'<table class="table_all" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px;margin-bottom: 0px"><tr><td width="120px" class="td_table_1" style="text-align: right">申请人：</td><td width="30%" class="td_table_2" align="left"></td><td width="120px" class="td_table_1" style="text-align: right">申请时间：</td><td width="30%" class="td_table_2" align="left"></td></tr></table><p class="ue_t"><br /></p>'
    },
    {
        "pre":"pre2.gif",
        'title':lang.blog,
        'preHtml':'<h1 label="Title center" name="tc" style="border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;text-align:center;margin:0px 0px 20px;"><span style="color:#c0504d;">模板三</span></h1><p style="text-align:center;"><strong class=" ">嵌套表格布局模板</strong></p><p style="text-indent:2em;">嵌套表格布局模板适用于存在主从业务的表单布局</p><br />',
        "html":'<h1 class="ue_t" label="Title center" name="tc" style="border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;text-align:center;margin:0px 0px 20px;"><span style="font-size: 24px">标题</span><br></h1><table width="100%"><tr><td width="20%" style="text-align: right">申请人：</td><td width="30%"></td><td width="20%" style="text-align: right">申请时间：</td><td width="30%"></td></tr></table><p class="ue_t"><br /></p>'
    }
];