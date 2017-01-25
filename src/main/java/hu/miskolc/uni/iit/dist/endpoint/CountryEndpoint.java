//package hu.miskolc.uni.iit.dist.endpoint;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//import hu.miskolc.uni.iit.dist.domain.GetCountryRequest;
//import hu.miskolc.uni.iit.dist.domain.GetCountryResponse;
//import hu.miskolc.uni.iit.dist.repository.CountryRepository;
//
//@Endpoint
//public class CountryEndpoint
//{
//	private static final String NAMESPACE_URI = "http://iit.uni.miskolc.hu/dist/domain";
//
//	private CountryRepository countryRepository;
//
//	@Autowired
//	public CountryEndpoint(CountryRepository countryRepository) {
//		this.countryRepository = countryRepository;
//	}
//
//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
//	@ResponsePayload
//	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
//		GetCountryResponse response = new GetCountryResponse();
//		response.setCountry(countryRepository.findCountry(request.getName()));
//
//		return response;
//	}
//}