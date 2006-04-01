package dk.i1.diameter.node;
import java.util.Random;

class NodeState {
	private int state_id;
	private int end_to_end_identifier;
	private int session_id_high;
	private int session_id_low;
	
	NodeState() {
		int now = (int)(System.currentTimeMillis()/1000);
		state_id = now;
		end_to_end_identifier = (now<<20) | (new Random().nextInt() & 0x000FFFFF);
		session_id_high = now;
		session_id_low = 0;
	}
	
	public int stateId() {
		return state_id;
	}
	
	public synchronized int nextEndToEndIdentifier() {
		int v = end_to_end_identifier;
		end_to_end_identifier++;
		return v;
	}
	
	synchronized String nextSessionId_second_part() {
		int l = session_id_low;
		int h = session_id_high;
		session_id_low++;
		if(session_id_low==0)
			session_id_high++;
		return h + ";" + l;
	}
}
