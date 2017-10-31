package com.mgcp.message.parameter;

import com.mgcp.message.command.MGCPCommand;

public class Test {
	public static void main(String[] args) throws Exception {
		// String parameterString = "N: ca@ca1.whatever.net:5678";
		//
		// MGCPParameter mgcpParameter = MGCPParameter.parse(parameterString);
		//
		// NotifiedEntity notifiedEntity = (NotifiedEntity)
		// mgcpParameter.getParameterValue().getValue();
		// //System.out.println(notifiedEntity.getLocalName());
		// //System.out.println(notifiedEntity.getLocalName().getLocalNameParts().get(0));
		// //System.out.println(notifiedEntity.getDomainName());
		// //System.out.println(notifiedEntity.getPortNumber());

		String NTFY = "NTFY 2002 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "N: ca@ca1.whatever.net:5678\n" + "X: 0123456789AC\n" + "O: L/hd,D/9,D/1,D/2,D/0,D/1,D/8,D/2,D/9,D/4,D/2,D/6,D/6\n";
		MGCPCommand mgcpCommand = MGCPCommand.parse(NTFY);
		 System.out.println(mgcpCommand);

		String CRCX01 = "CRCX 1204 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "C: A3C47F21456789F0\n" + "L: p:10, a:PCMU\n" + "M: recvonly\n";
		mgcpCommand = MGCPCommand.parse(CRCX01);
		// System.out.println(mgcpCommand);

		String CRCX02 = "CRCX 1205 aaln/1@rgw-2569.whatever.net MGCP 1.0\n" + "C: A3C47F21456789F0\n" + "L: p:10, a:PCMU\n" + "M: sendrecv\n" + "X: 0123456789AD\n" + "R: L/hd\n" + "S: L/rg\n";
		mgcpCommand = MGCPCommand.parse(CRCX02);
		// System.out.println(mgcpCommand);

		String CRCX03 = "CRCX 1206 aaln/1@rgw-2569.whatever.net MGCP 1.0\n" + "K: 1205\n" + "C: A3C47F21456789F0\n" + "L: p:10, a:PCMU\n" + "M: inactive\n";
		mgcpCommand = MGCPCommand.parse(CRCX03);
		// System.out.println(mgcpCommand);

		String MDCX01 = "MDCX 1209 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "C: A3C47F21456789F0\n" + "I: FDE234C8\n" + "N: ca@ca1.whatever.net\n" + "M: sendrecv\n";
		mgcpCommand = MGCPCommand.parse(MDCX01);
		// System.out.println(mgcpCommand);

		String MDCX02 = "MDCX 1210 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "C: A3C47F21456789F0\n" + "I: FDE234C8\n" + "M: recvonly\n" + "X: 0123456789AE\n" + "R: L/hu\n" + "S: G/rt\n";
		mgcpCommand = MGCPCommand.parse(MDCX02);
		// System.out.println(mgcpCommand);

		String DLCX01 = "DLCX 1210 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "C: A3C47F21456789F0\n" + "I: FDE234C8\n";
		mgcpCommand = MGCPCommand.parse(DLCX01);
		// System.out.println(mgcpCommand);

		String DLCX02 = "DLCX 1210 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "C: A3C47F21456789F0\n" + "I: FDE234C8\n" + "E: 900 - Hardware error\n"
				+ "P: PS=1245, OS=62345, PR=780, OR=45123, PL=10, JI=27, LA=48\n";
		mgcpCommand = MGCPCommand.parse(DLCX02);
		// System.out.println(mgcpCommand);

		String DLCX03 = "DLCX 1210 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "C: A3C47F21456789F0\n";
		mgcpCommand = MGCPCommand.parse(DLCX03);
		// System.out.println(mgcpCommand);

		String DCLX04 = "DLCX 1210 aaln/*@rgw-2567.whatever.net MGCP 1.0\n";
		mgcpCommand = MGCPCommand.parse(DCLX04);
		// System.out.println(mgcpCommand);

		String AUEP01 = "AUEP 1200 *@rgw-2567.whatever.net MGCP 1.0";
		mgcpCommand = MGCPCommand.parse(AUEP01);
		// System.out.println(mgcpCommand);

		String AUEP02 = "AUEP 1201 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "F: A\n";
		mgcpCommand = MGCPCommand.parse(AUEP02);
		// System.out.println(mgcpCommand);

		String AUEP03 = "AUEP 2002 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "F: R,D,S,X,N,I,T,O,ES\n";
		mgcpCommand = MGCPCommand.parse(AUEP03);
		// System.out.println(mgcpCommand);

		String AUCX01 = "AUCX 2003 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "I: 32F345E2\n" + "F: C,N,L,M,LC,P\n";
		mgcpCommand = MGCPCommand.parse(AUCX01);
		// System.out.println(mgcpCommand);

		String AUCX02 = "AUCX 2003 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "I: 32F345E2\n" + "F: C,N,L,M,LC,P\n";
		mgcpCommand = MGCPCommand.parse(AUCX02);
		// System.out.println(mgcpCommand);

		String AUCX03 = "AUCX 1203 aaln/2@rgw-2567.whatever.net MGCP 1.0\n" + "I: FDE234C8\n" + "F: RC,LC\n";
		mgcpCommand = MGCPCommand.parse(AUCX03);
		// System.out.println(mgcpCommand);

		String RSIP01 = "RSIP 1200 aaln/1@rgw-2567.whatever.net MGCP 1.0\n" + "RM: graceful\n" + "RD: 300\n";
		mgcpCommand = MGCPCommand.parse(RSIP01);
		// System.out.println(mgcpCommand);

		String RSIP02 = "RSIP 1204 *@rgw-2567.whatever.net MGCP 1.0\n" + "RM: restart\n" + "RD: 0\n";
		mgcpCommand = MGCPCommand.parse(RSIP02);
		System.out.println(mgcpCommand);
	}

}
