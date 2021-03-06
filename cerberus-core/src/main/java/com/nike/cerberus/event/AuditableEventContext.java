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

package com.nike.cerberus.event;

import com.nike.cerberus.domain.CerberusAuthToken;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;

/** An event that can be used to describe what a principal is doing with the API */
@Data
@Builder
public class AuditableEventContext implements Serializable {

  private static final long serialVersionUID = 2906382176272161512L;
  public static final String UNKNOWN = "_unknown";

  private Object principal;
  private String traceId;
  private String ipAddress;
  private String xForwardedFor;
  private String clientVersion;
  private String method;
  private String path;
  private String action;
  private String eventName;
  private String originatingClass;
  private String sdbNameSlug;
  @Builder.Default private OffsetDateTime timestamp = OffsetDateTime.now(ZoneId.of("UTC"));
  private boolean success;
  private String version;
  private int statusCode;

  public Optional<CerberusAuthToken> getPrincipalAsCerberusPrincipal() {
    return principal instanceof CerberusAuthToken
        ? Optional.of((CerberusAuthToken) principal)
        : Optional.empty();
  }

  public String getPrincipalName() {
    return principal instanceof CerberusAuthToken
        ? ((CerberusAuthToken) principal).getPrincipal()
        : principal instanceof String
            ? (String) principal
            : principal != null ? principal.toString() : "Unknown";
  }

  public String getEventAsString() {
    return eventName
        + ", "
        + "Principal: "
        + getPrincipalName()
        + ", "
        + "Action: "
        + '\''
        + action
        + "\', "
        + "Method: "
        + method
        + ", "
        + "Status Code: "
        + statusCode
        + ", "
        + "Was Success: "
        + success
        + ", "
        + "Path: "
        + path
        + ", "
        + "IP Address: "
        + ipAddress
        + ", "
        + "X-Forwarded-For: "
        + xForwardedFor
        + ", "
        + "Client Version: "
        + clientVersion
        + ", "
        + "Cerberus Version: "
        + version
        + ", "
        + "Originating Class: "
        + originatingClass
        + ", "
        + "SDB Name Slug: "
        + sdbNameSlug
        + ", "
        + "Trace ID: "
        + traceId
        + ", "
        + "Event Timestamp: "
        + timestamp.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm:ss a Z"));
  }
}
