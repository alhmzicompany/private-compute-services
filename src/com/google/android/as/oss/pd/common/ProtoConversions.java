/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.as.oss.pd.common;

import com.google.android.as.oss.pd.api.proto.BlobConstraints.Client;
import com.google.android.as.oss.pd.api.proto.BlobConstraints.ClientGroup;
import com.google.android.as.oss.pd.api.proto.BlobConstraints.DeviceTier;
import com.google.android.as.oss.pd.api.proto.DownloadBlobRequest;
import com.google.android.as.oss.pd.api.proto.DownloadBlobResponse;
import com.google.common.collect.ImmutableBiMap;
import java.util.Optional;

/**
 * Provides utility methods to convert some fields in {@link DownloadBlobRequest}/ {@link
 * DownloadBlobResponse} from/to their protobuf representation.
 */
public final class ProtoConversions {

  private final ImmutableBiMap<Client, String> clientToClientId;

  public ProtoConversions(ImmutableBiMap<Client, String> clientToClientId) {
    this.clientToClientId = clientToClientId;
  }

  private static final ImmutableBiMap<DeviceTier, String> DEVICE_TIER_TO_STRING =
      ImmutableBiMap.of(
          DeviceTier.UNKNOWN, "",
          DeviceTier.ULTRA_LOW, "Ultra Low",
          DeviceTier.LOW, "Low",
          DeviceTier.MID, "Mid",
          DeviceTier.HIGH, "High",
          DeviceTier.ULTRA, "Ultra");

  private static final ImmutableBiMap<ClientGroup, String> CLIENT_GROUP_TO_STRING =
      ImmutableBiMap.of(
          ClientGroup.ALL, "all",
          ClientGroup.BETA, "beta",
          ClientGroup.ALPHA, "alpha");

  public Optional<String> toClientIdString(Client client) {
    return Optional.ofNullable(clientToClientId.get(client));
  }

  public Optional<Client> fromClientIdString(String clientId) {
    return Optional.ofNullable(clientToClientId.inverse().get(clientId));
  }

  public static Optional<String> toDeviceTierString(DeviceTier deviceTier) {
    return Optional.ofNullable(DEVICE_TIER_TO_STRING.get(deviceTier));
  }

  public static Optional<DeviceTier> fromDeviceTierString(String deviceTier) {
    return Optional.ofNullable(DEVICE_TIER_TO_STRING.inverse().get(deviceTier));
  }

  public static Optional<String> toClientGroupString(ClientGroup clientGroup) {
    return Optional.ofNullable(CLIENT_GROUP_TO_STRING.get(clientGroup));
  }

  public static Optional<ClientGroup> fromClientGroupString(String clientGroup) {
    return Optional.ofNullable(CLIENT_GROUP_TO_STRING.inverse().get(clientGroup));
  }
}
