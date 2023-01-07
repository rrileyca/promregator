package org.cloudfoundry.promregator.cfaccessor;

import java.util.Set;

import org.cloudfoundry.client.v2.info.GetInfoResponse;
import org.cloudfoundry.client.v3.applications.ListApplicationProcessesResponse;
import org.cloudfoundry.client.v3.applications.ListApplicationsResponse;
import org.cloudfoundry.client.v3.organizations.ListOrganizationDomainsResponse;
import org.cloudfoundry.client.v3.routes.ListRoutesResponse;
import org.cloudfoundry.promregator.internalmetrics.InternalMetrics;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class CFAccessorCacheCaffeineSpringApplication {

	@Bean
	public InternalMetrics internalMetrics() {
		return Mockito.mock(InternalMetrics.class);
	}
	
	public static class ParentMock implements CFAccessor {

		@Override
		public Mono<GetInfoResponse> getInfo() {
			return Mono.just(GetInfoResponse.builder().build());
		}

		@Override
		public Mono<org.cloudfoundry.client.v3.organizations.ListOrganizationsResponse> retrieveOrgIdV3(String orgName) {
			return Mono.just(org.cloudfoundry.client.v3.organizations.ListOrganizationsResponse.builder().build());
		}

		@Override
		public Mono<org.cloudfoundry.client.v3.organizations.ListOrganizationsResponse> retrieveAllOrgIdsV3() {
			return Mono.just(org.cloudfoundry.client.v3.organizations.ListOrganizationsResponse.builder().build());
		}

		@Override
		public Mono<org.cloudfoundry.client.v3.spaces.ListSpacesResponse> retrieveSpaceIdV3(String orgId, String spaceName) {
			return Mono.just(org.cloudfoundry.client.v3.spaces.ListSpacesResponse.builder().build());
		}

		@Override
		public Mono<org.cloudfoundry.client.v3.spaces.ListSpacesResponse> retrieveSpaceIdsInOrgV3(String orgId) {
			return Mono.just(org.cloudfoundry.client.v3.spaces.ListSpacesResponse.builder().build());
		}

		@Override
		public Mono<ListApplicationsResponse> retrieveAllApplicationsInSpaceV3(String orgId, String spaceId) {
			return Mono.just(ListApplicationsResponse.builder().build());
		}

		@Override
		public Mono<ListOrganizationDomainsResponse> retrieveAllDomainsV3(String orgId) {
			return Mono.just(ListOrganizationDomainsResponse.builder().build());
		}

		@Override
		public Mono<ListRoutesResponse> retrieveRoutesForAppId(String appId) {
			return Mono.just(ListRoutesResponse.builder().build());
		}

		@Override
		public void reset() {
			// nothing to be done
		}

		@Override
		public Mono<ListRoutesResponse> retrieveRoutesForAppIds(Set<String> appIds) {
			return Mono.just(ListRoutesResponse.builder().build());
		}

		@Override
		public Mono<ListApplicationProcessesResponse> retrieveWebProcessesForAppId(String applicationId) {
			return Mono.just(ListApplicationProcessesResponse.builder().build());
		}
	}
	
	@Bean
	public CFAccessor parentMock() {
		return Mockito.spy(new ParentMock());
	}
	
	@Bean
	public CFAccessorCacheCaffeine subject(@Qualifier("parentMock") CFAccessor parentMock) {
		return new CFAccessorCacheCaffeine(parentMock);
	}
	
}
