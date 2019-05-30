package Client.board.distributor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Client.board.connect.ClientThread;
import Client.board.controller.CheckIdController;
import Client.board.controller.ImageController;
import Client.board.controller.InsertCommentController;
import Client.board.controller.LoginController;
import Client.board.controller.RegistController;
import Client.board.controller.SelectAllCommentController;
import Client.board.controller.SelectAllController;
import Client.board.controller.WriteController;

public class ClientDistributor {
	private ClientThread clientThread;

	JSONParser jsonParser;
	JSONArray array;
	JSONObject obj;

	// Controller »ý¼º
	private CheckIdController checkIdController;
	private RegistController registController;
	private LoginController loginController;
	private WriteController writeController;
	private ImageController imageController;
	private SelectAllController selectAllController;
	private InsertCommentController insertCommentController;
	private SelectAllCommentController selectAllCommentController;

	public ClientDistributor(ClientThread clientThread) {
		this.clientThread = clientThread;
		jsonParser = new JSONParser();

		// Controller
		checkIdController = new CheckIdController(this);
		registController = new RegistController(this);
		loginController = new LoginController(this);
		writeController = new WriteController(this);
		imageController = new ImageController(this);
		selectAllController = new SelectAllController(this);
		insertCommentController = new InsertCommentController(this);
		selectAllCommentController = new SelectAllCommentController(this);
	}

	public void distribute(String msg) {

		JSONObject obj = null;
		String requestType = null;

		System.out.println("ClientDistributor.distribute() : msg : " + msg);

		try {
			obj = (JSONObject) jsonParser.parse(msg);
			requestType = (String) obj.get("requestType");

			System.out.println("ClientDistributor.distribute() :  requestType " + requestType);

			if (requestType.equals("checkId")) {
				System.out.println("ClientDistributor.distribute() / checkId ==>"+requestType);
				checkIdController.checkId(obj);
			} else if (requestType.equals("regist")) {
				System.out.println("ClientDistributor.distribute() / regist ==>"+requestType);
				registController.regist(obj);
			} else if (requestType.equals("login")) {
				System.out.println("ClientDistributor.distribute() / login ==>"+requestType);
				loginController.login(obj);
			} else if (requestType.equals("write")) {
				System.out.println("ClientDistributor.distribute() / write ==>"+requestType);
				writeController.write(obj);
			} else if (requestType.equals("write")) {
				System.out.println("ClientDistributor.distribute() / image ==>"+requestType);
				imageController.getImage(obj);
			}else if (requestType.equals("selectAll")) {
				System.out.println("ClientDistributor.distribute() / selectAll ==>"+requestType);
				selectAllController.selectAll(obj);
			}else if (requestType.equals("insertComment")) {
				System.out.println("ClientDistributor.distribute() / insertComment ==>"+requestType);
				insertCommentController.insertComment(obj);
			}else if (requestType.equals("selectAllComment")) {
				System.out.println("ClientDistributor.distribute() / selectAllComment ==>"+requestType);
				selectAllCommentController.selectAllComment(obj);
			}
		} catch (

		ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ClientThread getClientThread() {
		return clientThread;
	}

	public void setClientThread(ClientThread clientThread) {
		this.clientThread = clientThread;
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
