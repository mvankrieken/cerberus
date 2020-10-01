/*
 * Copyright (c) 2020 Nike, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nike.cerberus.auth.connector;

/** Represents a MFA device returned by the auth connector. */
public class AuthMfaDevice {

  private String id;

  private String name;

  private boolean requiresTrigger;

  private boolean isPush;

  public String getId() {
    return id;
  }

  public AuthMfaDevice setId(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public AuthMfaDevice setName(String name) {
    this.name = name;
    return this;
  }

  public boolean getRequiresTrigger() {
    return requiresTrigger;
  }

  public AuthMfaDevice setRequiresTrigger(boolean requiresTrigger) {
    this.requiresTrigger = requiresTrigger;
    return this;
  }

  public boolean getIsPush() {
    return isPush;
  }

  public AuthMfaDevice setIsPush(boolean isPush) {
    this.isPush = isPush;
    return this;
  }
}
