package org.cloudfoundry.promregator.cfaccessor;

import org.cloudfoundry.client.v2.applications.ListApplicationsResponse;
import org.cloudfoundry.client.v2.info.GetInfoResponse;
import org.cloudfoundry.client.v2.organizations.ListOrganizationsResponse;
import org.cloudfoundry.client.v2.spaces.GetSpaceSummaryResponse;
import org.cloudfoundry.client.v2.spaces.ListSpacesResponse;
import org.cloudfoundry.client.v3.applications.ListApplicationRoutesResponse;
import org.cloudfoundry.client.v3.domains.ListDomainsResponse;
import org.cloudfoundry.client.v3.routes.ListRoutesResponse;

import reactor.core.publisher.Mono;

public interface CFAccessor {
	Mono<GetInfoResponse> getInfo();
	
	Mono<ListOrganizationsResponse> retrieveOrgId(String orgName);
	
	Mono<ListOrganizationsResponse> retrieveAllOrgIds();

	Mono<ListSpacesResponse> retrieveSpaceId(String orgId, String spaceName);
	
	Mono<ListSpacesResponse> retrieveSpaceIdsInOrg(String orgId);

	Mono<ListApplicationsResponse> retrieveAllApplicationIdsInSpace(String orgId, String spaceId);

  Mono<GetSpaceSummaryResponse> retrieveSpaceSummary(String spaceId);
  
  Mono<ListDomainsResponse> retrieveDomains();

  Mono<ListApplicationRoutesResponse> retrieveAppRoutes(String appId);
	
	void reset();
}