/*package com.Utilities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.operations.Common.ReadStats;
import com.operations.Common.ReadUserconfig;

public class PunchOutURLCreation {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	public void GeneratePunchOutURL(){

		try {

			ReadStats rs = new ReadStats();
			String Mega = null;
			DataOutputStream out = null;
			URL url = null;
			HttpURLConnection con = null ;
			String PunchOutURLForTest=null;
			rs.getRepositoryValues();
			String Envtype=rs.Envtype;

			System.out.println("START");
			String SetfilePath = "SetAppData.properties";
			BufferedWriter bw = new BufferedWriter((new FileWriter(SetfilePath)));

			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub

				}
				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub

				}
			}
			};

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			if(Envtype.equalsIgnoreCase("ECTEST")) {

				url = new URL("https://b2btest.kamandirect.com/invoke/wm.b2b.cxml/receiveCXML");
				con = (HttpURLConnection) url.openConnection();
				con.setAllowUserInteraction(false);
				con.setDoOutput(true);

				con.setRequestMethod("POST");
				con.setRequestProperty("Accept", "application/cXML");
				con.setRequestProperty("Content-Type", "text/xml; charset=\"utf-8\"");

				con.setInstanceFollowRedirects(true);
				Mega="<?xml version=\"1.0\"?>\r\n" + 
						"<!DOCTYPE cXML SYSTEM \"http://xml.cxml.org/schemas/cXML/1.1.008/cXML.dtd\">\r\n" + 
						"<cXML version=\"1.1.008\" xml:lang=\"en-US\" payloadID=\"933695135608.677295401@jlee\" timestamp=\"2000-08-15T08:45:35-07:00\">\r\n" + 
						"  <Header>\r\n" + 
						"    <From>\r\n" + 
						"      <Credential domain=\"NetworkId\">\r\n" + 
						"        <Identity>AN01000017969</Identity>\r\n" + 
						"      </Credential>\r\n" + 
						"    </From>\r\n" + 
						"    <To>\r\n" + 
						"      <Credential domain=\"DUNS\">\r\n" + 
						"        <Identity>077316412</Identity>\r\n" + 
						"      </Credential>\r\n" + 
						"    </To>\r\n" + 
						"    <Sender>\r\n" + 
						"      <Credential domain=\"AribaNetworkUserId\">\r\n" + 
						"        <Identity>sysadmin@ariba.com</Identity>\r\n" + 
						"        <SharedSecret>kaman2direct</SharedSecret>\r\n" + 
						"      </Credential>\r\n" + 
						"      <UserAgent>Ariba ORMS 6.1</UserAgent>\r\n" + 
						"    </Sender>\r\n" + 
						"  </Header>\r\n" + 
						"	\r\n" + 
						"  <Request>\r\n" + 
						"    <PunchOutSetupRequest operation=\"create\">\r\n" + 
						"      <BuyerCookie>1CX3L4843PPZO</BuyerCookie>\r\n" + 
						"      <Extrinsic name=\"UserEmail\">SysAdmin@NationalGypsum.com</Extrinsic>\r\n" + 
						"      <Extrinsic name=\"UniqueName\">bep123</Extrinsic>\r\n" + 
						"      <Extrinsic name=\"Department\">NGALL:54100</Extrinsic>\r\n" + 
						"      <BrowserFormPost>\r\n" + 
						"        <URL>http://kit08wmisd:5556/web/viewshoppingcart.jsp</URL>\r\n" + 
						"      </BrowserFormPost>\r\n" + 
						"      <Contact role=\"endUser\">\r\n" + 
						"        <Name xml:lang=\"en\">CLTRGS:Syracuse,Ray</Name>\r\n" + 
						"        <Email>SysAdmin@NationalGypsum.com</Email>\r\n" + 
						"      </Contact>\r\n" + 
						"      <SupplierSetup>\r\n" + 
						"      \r\n" + 
						"        <URL>http://www.workchairs.com/punchout.asp</URL>\r\n" + 
						"      </SupplierSetup>\r\n" + 
						"      <ShipTo>\r\n" + 
						"        <Address addressID=\"NGALL:BAL13\">\r\n" + 
						"            <Name xml:lang=\"en\">NGALL:CLT11</Name>\r\n" + 
						"            <PostalAddress>\r\n" + 
						"                <DeliverTo>National Gypsum Co.</DeliverTo>\r\n" + 
						"                <Street>Mail Room, Deliveries</Street>\r\n" + 
						"                <Street>2001 Rexfood Road</Street>\r\n" + 
						"                <City>Charlotte</City>\r\n" + 
						"                <State>NC</State>\r\n" + 
						"                <PostalCode>28211</PostalCode>\r\n" + 
						"                <Country isoCountryCode=\"US\">United States</Country>\r\n" + 
						"            </PostalAddress>\r\n" + 
						"        </Address>\r\n" + 
						"      </ShipTo>\r\n" + 
						"    </PunchOutSetupRequest>\r\n" + 
						"  </Request>\r\n" + 
						"</cXML>\r\n" + 
						"\r\n" + 
						"	\r\n" + 
						"";

				out = new DataOutputStream(con.getOutputStream());
				out.writeBytes(Mega);
			}

			else if(Envtype.equalsIgnoreCase("LogixalQA")) {

				url = new URL("https://192.168.15.18/storeus/catalog/b2bPunchoutSetup.jsp");
				con = (HttpURLConnection) url.openConnection();

				con.setRequestMethod("POST");

				con.setInstanceFollowRedirects(true);

				int low = 1;
				int high = 100000;
				Random random = new Random();
				int result = random.nextInt(high-low) + low;
				String str =String.valueOf(result);

				String uniqueStr = str;
				Map<String, String> parameters = new HashMap<>();
				parameters.put("COMPANY_ID", "cceWDI");
				parameters.put("LOGIN_ID", "jpj"+uniqueStr);
				parameters.put("PASSWORD", "topfuel");
				parameters.put("WM_HOOK_URL", "https://192.168.15.18.com/");
				parameters.put("MARKETPLACE_ID", "C1");
				parameters.put("DESTROY_CART_AT_LOGIN", "1");
				parameters.put("ALLOW_CART_EDIT", "1");
				parameters.put("SEND_CART_BACK", "1");
				parameters.put("TOTAL_ITEMS", "0");;
				parameters.put("B2B_USER_FLAG", "1");
				parameters.put("ALLOW_CHECKOUT_FLAG", "1");
				parameters.put("UOM_TYPE_FLAG", "ANSI");

				parameters.put("USER_EMAIL", "jpj"+uniqueStr+"@email.com");
				parameters.put("MEMBER", "1");
				parameters.put("LOCATION_ID", "00360");
				parameters.put("CUSTOMER_ID", "132120");
				parameters.put("CTM_LOCATION_NAME", "Alsip IL Production-"+ uniqueStr);

				parameters.put("USER_FIRST_NAME", "T1"+uniqueStr);
				parameters.put("USER_MIDDLE_NAME", "");
				parameters.put("USER_LAST_NAME", "Y"+uniqueStr);
				parameters.put("USER_TELEPHONE", "1234567890");
				parameters.put("USER_MOBILE_NUMBER", "");
				parameters.put("DEF_METH_OF_SHPMNT", "UPSGRD");
				parameters.put("DEF_METH_OF_PMNT", "2"); //required for old
				parameters.put("METH_OF_PMNT_ALLOW", "2,3,8"); //required for old
				parameters.put("SHOW_AVLBTY_QTY","1"); //required for old
				parameters.put("SHOW_AVLBTY_DETAIL","1"); //required for old
				parameters.put("SEC_CUST_PART_INTC", "");
				parameters.put("DISABLE_USER", "0");
				parameters.put("PAGER_NUMBER", "");
				parameters.put("DISABLE_ORDER_EMAIL", "0");
				parameters.put("DISABLE_SHPMNT_EMAIL", "1");
				parameters.put("PASSWD_REMIND_PHRASE", "");

				//Added for test on 4/25/2018 after latest "fix"
				parameters.put("COMPANY_NAME", "Test1");
				parameters.put("USER_TITLE", "");
				parameters.put("USER_FAX", "");
				parameters.put("SHIP_ADDRESS_FIRST_NAME", "");//"John");
				parameters.put("SHIP_ADRESS_MID_NAME", "");//"");
				parameters.put("SHIP_ADDRESS_LAST_NAME", "");//"Jones");
				parameters.put("SHIP_ADDRESS_LINE1", "");//"1 Main Street");
				parameters.put("SHIP_ADDRESS_LINE2", "");//"");
				parameters.put("SHIP_ADDRESS_CITY", "");//"Boston");
				parameters.put("SHIP_ADDRESS_STATE", "");//"MA");
				parameters.put("SHIP_ADDRESS_POSTAL", "");//"02152");
				parameters.put("SHIP_ADDRESS_CNTRY", "");//"USA");
				parameters.put("SHIP_ADDRESS_PHONE", "");//"4134134130");
				parameters.put("SHIP_ADDRESS_FAX", "");

				parameters.put("BILL_ADDRESS_FIRST_NAME", "");
				parameters.put("BILL_ADDRESS_MID_NAME", "");
				parameters.put("BILL_ADDRESS_LAST_NAME", "");
				parameters.put("BILL_ADDRESS_LINE1", "");
				parameters.put("BILL_ADDRESS_LINE2", "");
				parameters.put("BILL_ADDRESS_CITY", "");
				parameters.put("BILL_ADDRESS_STATE", "");
				parameters.put("BILL_ADDRESS_POSTAL", "");
				parameters.put("BILL_ADDRESS_CNTRY", "");
				parameters.put("BILL_ADDRESS_PHONE", "");
				parameters.put("BILL_ADDRESS_FAX", "");

				parameters.put("RESERVED_FIELD1", "");
				parameters.put("RESERVED_FIELD2", "https://149.158.22.11:4436/web/viewshoppingcart.jsp?testvariable=ABC&amp;amptest=true");
				parameters.put("RESERVED_FIELD3", "testdata");
				parameters.put("RESERVED_FIELD4", "cccWDI");
				parameters.put("RESERVED_FIELD5", "ANDI");
				parameters.put("RESERVED_FIELD6", "");
				parameters.put("RESERVED_FIELD7", "");
				parameters.put("RESERVED_FIELD8", "");
				parameters.put("RESERVED_FIELD9", "");
				parameters.put("RESERVED_FIELD10", "");
				//parameters.put("name", "value");
				con.setDoOutput(true);
				out = new DataOutputStream(con.getOutputStream());
				out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			}

			else if (Envtype.equalsIgnoreCase("Prod")) {

				System.out.println("Punchout testing not applicable on Production environment..!!!");


			}
			//con.setDoOutput(true);
			//DataOutputStream out = new DataOutputStream(con.getOutputStream());
			//out.writeBytes(Mega);

			if (!(Envtype.equalsIgnoreCase("Prod"))) {

				out.flush();
				out.close();

				con.setConnectTimeout(5000);
				con.setReadTimeout(5000);

				int status = con.getResponseCode();

				switch (status) {
				case HttpURLConnection.HTTP_MOVED_TEMP:
					String location = con.getHeaderField("Location");
					//	System.out.println("location = " + location);
				default:
				}

				//System.out.println("Status = " + status);

				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				con.disconnect();

				//System.out.println(content);

				if (Envtype.equalsIgnoreCase("ECTEST")) {

					String PunchURL=content.toString();

					PunchOutURLForTest = getPunchOutURL(PunchURL);
				}
				else if (Envtype.equalsIgnoreCase("LogixalQA")) {

					String StrContent =String.valueOf(content);
					String[] parts = StrContent.split("=", 3);

					PunchOutURLForTest = parts[2];

					System.out.println(PunchOutURLForTest);
				}

				bw.write("PunchOutURL="+ PunchOutURLForTest);
				bw.write(System.lineSeparator());
				bw.close();


			}

			System.out.println("END");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getPunchOutURL(String PunchString) {
		// TODO Auto-generated method stub

		String PUrl=null;
		try   
		{  

			FileWriter Pwriter = new FileWriter("./AutoGendata/PunchOutFile/PunchOutURL.xml", false);
			Pwriter.write(PunchString);
			Pwriter.flush();
			Pwriter.close();

			Thread.sleep(2000);

			File file = new File("./AutoGendata/PunchOutFile/PunchOutURL.xml");  

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize();  
			NodeList nodeList = doc.getElementsByTagName("StartPage");  

			for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
				Node node = nodeList.item(itr);  

				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					Element eElement = (Element) node;  
					PUrl=eElement.getElementsByTagName("URL").item(0).getTextContent();
					System.out.println(PUrl);  

				}  
			}



		}   
		catch (Exception e)   
		{  
			e.printStackTrace();  
		}
		return PUrl;
	}  


	static class ParameterStringBuilder {
		public static String getParamsString(Map<String, String> params) 
				throws UnsupportedEncodingException{
			StringBuilder result = new StringBuilder();

			for (Map.Entry<String, String> entry : params.entrySet()) {
				result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
				result.append("=");
				result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				result.append("&");
			}

			String resultString = result.toString();
			return resultString.length() > 0
					? resultString.substring(0, resultString.length() - 1)
							: resultString;
		}


	}
}
*/