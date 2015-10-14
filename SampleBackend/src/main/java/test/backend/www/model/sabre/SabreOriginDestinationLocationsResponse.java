/*
 * Sabre Inc. All rights reserved.
 * 
 * THE SOFTWARE, SAMPLE CODES AND ANY COMPILED PROGRAMS CREATED USING THE
 * SOFTWARE ARE FURNISHED "AS IS" WITHOUT WARRANTY OF ANY KIND, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE. NO ORAL OR WRITTEN INFORMATION OR ADVICE GIVEN BY SABRE,
 * ITS AGENTS OR EMPLOYEES SHALL CREATE A WARRANTY OR IN ANY WAY INCREASE THE
 * SCOPE OF THIS WARRANTY, AND YOU MAY NOT RELY ON ANY SUCH INFORMATION OR
 * ADVICE. SABRE DOES NOT WARRANT, GUARANTEE, OR MAKE ANY REPRESENTATIONS
 * REGARDING THE USE, OR THE RESULTS OF THE USE, OF THE SOFTWARE, COMPILED
 * PROGRAMS CREATED USING THE SOFTWARE, OR WRITTEN MATERIALS IN TERMS OF
 * CORRECTNESS, ACCURACY, RELIABLITY, CURRENTNESS, OR OTHERWISE. THE ENTIRE RISK
 * AS TO THE RESULTS AND PERFORMANCE OF THE SOFTWARE AND ANY COMPILED
 * APPLICATIONS CREATED USING THE SOFTWARE IS ASSUMED BY YOU. BY YOUR USE, YOU
 * AGREE THAT NEITHER SABRE NOR ANYONE ELSE WHO HAS BEEN INVOLVED IN THE
 * CREATION, PRODUCTION OR DELIVERY OF THE SOFTWARE SHALL BE LIABLE FOR ANY
 * DIRECT, INDIRECT, CONSEQUENTIAL, OR INCIDENTAL DAMAGES (INCLUDING DAMAGES FOR
 * LOSS OF BUSINESS PROFITS, BUSINESS INTERRUPTION, LOSS OF BUSINESS
 * INFORMATION, AND THE LIKE) ARISING OUT OF THE USE OF OR INABILITY TO USE SUCH
 * PRODUCT EVEN IT SABRE HAS BEEN ADVISED OF THE POSSIBLITY OF SUCH DAMAGES.
 * THIS SOFTWARE IS OWNED AND COPYRIGHTED BY SABRE OR ITS THIRD PARTY SUPPLIERS.
 * YOUR LICENSE TO UTILIZE IT CONFERS NO OWNERSHIP RIGHTS IN THE SOFTWARE OR
 * THOSE PORTIONS YOU MAY USE IN A PROJECT. YOU AGREE TO INDEMNIFY AND HOLD
 * HARMLESS SABRE AND ITS AFFILIATES FOR ANY CLAIM BROUGHT AGAINST IT BASED UPON
 * YOUR USE OF THE SOFTWARE OR ANY COMPILED PROGRAMS CREATED USING THE SOFTWARE
 */

package test.backend.www.model.sabre;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SabreOriginDestinationLocationsResponse
{
  @Data
  public static class SabreLocation
  {
    @JsonProperty("AirportCode")
    private String airportCode;

    @JsonProperty("AirportName")
    private String airportName;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("CountryName")
    private String countryName;

    @JsonProperty("RegionName")
    private String regionName;
  }

  @Data
  public static class SabreOriginDestinationLocation
  {
    @JsonProperty("OriginDestinationLocations")
    private String originDestinationLocations;

    @JsonProperty("OriginLocation")
    private SabreLocation originLocation;

    @JsonProperty("DestinationLocation")
    private SabreLocation destinationLocation;

  }

  @JsonProperty("OriginDestinationLocations")
  private List<SabreOriginDestinationLocation> originDestinationLocations;

  @JsonProperty("Links")
  private List<SabreLink> links;
}