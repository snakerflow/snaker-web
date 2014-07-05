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

package org.snaker.framework.utils;

import httl.Engine;
import httl.Template;

import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

/**
 * httl工具类
 * @author yuqs
 * @since 1.0
 */
public class HttlUtils {
    private static Engine engine;

    public static Engine getEngine() {
        if(engine == null) {
            Properties props = new Properties();
            props.put("input.encoding", "UTF-8");
            props.put("output.encoding", "UTF-8");
            engine = Engine.getEngine(props);
        }
        return engine;
    }

    public static void render(String httl, Map<String, Object> args, OutputStream output) {
        try {
            Template template = getEngine().getTemplate(httl);
            template.render(args, output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
