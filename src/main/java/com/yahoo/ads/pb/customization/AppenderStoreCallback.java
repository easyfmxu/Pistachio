/*
 * Copyright 2014 Yahoo! Inc. Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or
 * agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and
 * limitations under the License. See accompanying LICENSE file.
 */

package com.yahoo.ads.pb.customization;
import com.yahoo.ads.pb.util.ConfigurationManager;
import com.yahoo.ads.pb.customization.CustomizationRegistry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URLClassLoader;
import java.net.URL;

public class AppenderStoreCallback implements StoreCallback {
    public boolean needCallback() {
        return true;
    }
    public byte[] onStore(byte[] key, byte[] currentValue, byte[] toStore) {
        if (currentValue == null)
            return toStore;
        byte[] newValue = new byte[currentValue.length + toStore.length];
        System.arraycopy(currentValue, 0, newValue, 0, currentValue.length);
        System.arraycopy(toStore, 0, newValue, currentValue.length, toStore.length);
        return newValue;
    }
}
