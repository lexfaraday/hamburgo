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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class DSCommHandler {

	public String getAuthToken(String apiEndPoint, String encodedCliAndSecret) {

		// receives : apiEndPoint (https://api.test.sabre.com)
		// encodedCliAndSecret : base64Encode(
		// base64Encode(V1:[user]:[group]:[domain]) + ":" +
		// base64Encode([secret]) )
		String strRet = null;

		try {

			URL urlConn = new URL(apiEndPoint + "/v2/auth/token");
			URLConnection conn = urlConn.openConnection();

			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);

			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization", "Basic " + encodedCliAndSecret);
			conn.setRequestProperty("Accept", "application/json");

			// send request
			DataOutputStream dataOut = new DataOutputStream(conn.getOutputStream());
			dataOut.writeBytes("grant_type=client_credentials");
			dataOut.flush();
			dataOut.close();

			// get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String strChunk = "";
			StringBuilder sb = new StringBuilder();
			while (null != (strChunk = rd.readLine())) {
				sb.append(strChunk);
			}

			// parse the token
			JSONObject respParser = new JSONObject(sb.toString());
			strRet = respParser.getString("access_token");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strRet;

	}

	@SuppressWarnings("deprecation")
	public String sendRequest(String payLoad, String authToken) throws IOException {
		URLConnection conn = null;
		String strRet = null;
		try {
			URL urlConn = new URL(payLoad);

			conn = null;
			conn = urlConn.openConnection();

			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);

			conn.setRequestProperty("Authorization", "Bearer " + authToken);
			conn.setRequestProperty("Accept", "application/json");

			DataInputStream dataIn = new DataInputStream(conn.getInputStream());
			String strChunk = "";
			StringBuilder sb = new StringBuilder("");
			while (null != (strChunk = dataIn.readLine())) {
				sb.append(strChunk);
			}

			strRet = sb.toString();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strRet;
	}

}