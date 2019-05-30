package server.board.distributor;

import javax.swing.JTextArea;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.board.distributor.controller.CheckIdController;
import server.board.distributor.controller.ImageController;
import server.board.distributor.controller.InsertCommentController;
import server.board.distributor.controller.LoginController;
import server.board.distributor.controller.RegistController;
import server.board.distributor.controller.SelectAllCommentController;
import server.board.distributor.controller.SelectAllController;
import server.board.distributor.controller.WriteController;
import server.board.main.ServerThread;


public class ServerDistributor {

	private ServerThread serverThread;
	private JSONParser jsonParser;
	private JSONObject obj;
	private JTextArea area;
	
	// Controller
	private CheckIdController checkIdController;
	private RegistController registController;
	private LoginController loginController;
	private WriteController writeController;
	private SelectAllController selectAllController;
	private ImageController imageController;
	private InsertCommentController insertCommentController;
	private SelectAllCommentController selectAllCommentController;
	
	public ServerDistributor(ServerThread serverThread) {
		this.serverThread = serverThread;

		area = serverThread.getArea();
		jsonParser = new JSONParser();
		
		// Controller 생성
		checkIdController = new CheckIdController(this);
		registController = new RegistController(this);
		loginController = new LoginController(this);
		writeController = new WriteController(this);
		selectAllController= new SelectAllController(this);
		imageController = new ImageController(this);
		insertCommentController = new InsertCommentController(this);
		selectAllCommentController = new SelectAllCommentController(this);
	}

	public void distribute(String msg) {
		/*
		 * System.out.println("ServerDistributor / distribute() / StringBuilder : "+sb.
		 * length()); String msg = sb.toString();
		 */
		System.out.println("ServerDistributor / distribute() / msg : "+msg.length());

			try {
				obj = (JSONObject) jsonParser.parse(msg);

				String requestType = (String) obj.get("requestType");
				area.append("ServerDistributor.distribute()/ requestType :  " + requestType+"\n");
				
				if(requestType.equals("checkId")) {
					area.append("ServerDistributor.distribute()/ checkId 진입 \n");
					checkIdController.checkId(obj);
				}else if(requestType.equals("regist")) {
					area.append("ServerDistributor.distribute()/ regist 진입 \n");
					registController.regist(obj);
				}else if(requestType.equals("login")) {
					area.append("ServerDistributor.distribute()/ login 진입 \n");
					loginController.login(obj);
				}else if(requestType.equals("write")) {
					area.append("ServerDistributor.distribute()/ write 진입 \n");
					writeController.write(obj);
				}else if(requestType.equals("image")) {
					area.append("ServerDistributor.distribute()/ image 진입 \n");
					imageController.imageTransaction(obj);
				}else if(requestType.equals("selectAll")) {
					area.append("ServerDistributor.distribute()/ selectAll 진입 \n");
					selectAllController.selectAll(obj);
				}else if(requestType.equals("insertComment")) {
					area.append("ServerDistributor.distribute()/ insertComment 진입 \n");
					insertCommentController.insertComment(obj);
				}else if(requestType.equals("selectAllComment")) {
					area.append("ServerDistributor.distribute()/ selectAllComment 진입 \n");
					selectAllCommentController.selectAllComment(obj);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	public ServerThread getServerThread() {
		return serverThread;
	}

	public void setServerThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}

	public JSONParser getJsonParser() {
		return jsonParser;
	}

	public void setJsonParser(JSONParser jsonParser) {
		this.jsonParser = jsonParser;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}

	public CheckIdController getCheckIdController() {
		return checkIdController;
	}

	public void setCheckIdController(CheckIdController checkIdController) {
		this.checkIdController = checkIdController;
	}

	public RegistController getRegistController() {
		return registController;
	}

	public void setRegistController(RegistController registController) {
		this.registController = registController;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public WriteController getWriteController() {
		return writeController;
	}

	public void setWriteController(WriteController writeController) {
		this.writeController = writeController;
	}

}
