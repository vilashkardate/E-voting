<%@page import="evoting.entity.CandidateDetails"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%
String userid = (String) session.getAttribute("userid");
if (userid == null) {
	response.sendRedirect("accessdenied.html");
	return;
}
String result = (String) request.getAttribute("result");
StringBuffer displayBlock = new StringBuffer();
if (result != null && result.equalsIgnoreCase("candidatelist"))
{
	ArrayList<String> candidateId = (ArrayList<String>)request.getAttribute("candidateid");
	displayBlock.append("<option value=''>Choose Id</option>");
	for (String cid : candidateId) {
		
		displayBlock.append("<option value='"+cid+"'>"+cid+"</option>");
	}
	JSONObject json = new JSONObject();
	json.put("cids", displayBlock.toString());
	out.println(json);
 
}
else if (result != null && result.equals("details"))
{
	CandidateDetails candidate = (CandidateDetails) request.getAttribute("candidate");
	String str = "<img src='data:image/jpg;base64," + candidate.getSymbol() + "'style='width:300px;height:200px;'/>";
	displayBlock.append("<table>"
                            +"<tr><th>User Id:</th><td>"+candidate.getUserid()+"</td></tr>"
                            +"<tr><th>Candidate Name:</th><td>"+candidate.getCandidateName()+"</td></tr>"
                            +"<tr><th>City:</th><td>"+candidate.getCity()+"</td></tr>"
                            +"<tr><th>Party:</th><td>"+candidate.getParty()+"</td></tr>"
                            +"<tr><th>Symbol:</th><td id='image' >"+str+"</td></tr>"
                            +"</table>");

	JSONObject json = new JSONObject();
	json.put("subdetails", displayBlock.toString());
	out.println(json);
}
	System.out.println("in admin show candidate");
	System.out.println(displayBlock);

%>