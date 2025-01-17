package org.knowm.xchange.enigma;

import org.knowm.xchange.enigma.dto.marketdata.EnigmaProduct;
import org.knowm.xchange.enigma.dto.marketdata.EnigmaProductMarketData;
import org.knowm.xchange.enigma.dto.trade.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface EnigmaAuthenticated extends Enigma {

  @GET
  @Path("product")
  List<EnigmaProduct> getProducts(@HeaderParam("Authorization") String accessToken)
      throws IOException;

  @GET
  @Path("spot/{product-id}")
  EnigmaProductMarketData getProductMarketData(
      @HeaderParam("Authorization") String accessToken, @PathParam("product-id") int productId)
      throws IOException;

  @GET
  @Path("risk/limit")
  Map<String, BigDecimal> getAccountRiskLimits(@HeaderParam("Authorization") String accessToken)
      throws IOException;

  @POST
  @Path("order/new")
  @Consumes(MediaType.APPLICATION_JSON)
  EnigmaOrderSubmission submitOrder(
      @HeaderParam("Authorization") String accessToken, EnigmaNewOrderRequest orderRequest)
      throws IOException;

  @POST
  @Path("rfq/new")
  @Consumes(MediaType.APPLICATION_JSON)
  EnigmaQuote askForQuote(
      @HeaderParam("Authorization") String accessToken, EnigmaQuoteRequest quoteRequest)
      throws IOException;

  @POST
  @Path("rfq/execute")
  @Consumes(MediaType.APPLICATION_JSON)
  EnigmaExecutedQuote executeQuoteRequest(
      @HeaderParam("Authorization") String accessToken, EnigmaExecuteQuoteRequest quoteRequest)
      throws IOException;

  @GET
  @Path("balance/{infra}")
  Map<String, BigDecimal> getBalance(
      @HeaderParam("Authorization") String accessToken, @PathParam("infra") String infrastructure)
      throws IOException;

  @GET
  @Path("withdrawal/list/{infra}")
  List<EnigmaWithdrawal> getAllWithdrawals(
      @HeaderParam("Authorization") String accessToken, @PathParam("infra") String infrastructure);

  @POST
  @Path("withdrawal/new")
  EnigmaWithdrawal withdrawal(
      @HeaderParam("Authorization") String accessToken, EnigmaWithdrawalRequest withdrawalRequest);
}
