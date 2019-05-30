# socket-project


프로젝트 주제	: <br>
Java Socket통신을 사용한 응용프로그램 만들기.<br>
<br>
프로젝트 설명 : <br>
소켓통신을 이용한 SNS 응용프로그램 구현<br>
로그인 처리를 통하여, 유저마다 자신의 사진 및 글이 담긴 게시물을 게시.<br>
각 게시물마다 로그인 계정으로 댓글 작성 가능.<br>
<br>

개발 툴	: Eclipse<br>
<br>
개발 기술 : JAVA, Oracle, mybatis, JSON <br>
<br>
프로젝트 기간	: 2019. 05 .15 ~ 2019.05.25 <br>

<h1>상 세 설 명</h1>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMjEg/MDAxNTU5MjUwNDYyNDM0.2Otf2bddtZ0Cds_WQKZnyJwJ0LyH7jGlpnx7ibmmpAgg.eq2RJ_dG7G1b9VDVgVh3kF5UW-mJEVkpFcV481eShmYg.PNG.wjddydwndi/%EC%9D%B4%EB%AF%B8%EC%A7%80_0.png?type=w773" width="100%">

<h3>[ 디자인 ]</h3>
<ul>- Java swing을 사용한 GUI 구현</ul>
<ul>- java.util.io 사용한 이미지 처리</ul>
<ul>- JFileChooser를 사용한 파일 선택 구현</ul>
<ul>- JTable을 사용한 댓글처리 </ul>
<ul>- 모든 페이지 및 클래스들을 관리하는 클래스를 정의하여, 페이지 및 로그인 정보 등 처리</ul>

<h3>[ DB 구조 ]</h3>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfNTIg/MDAxNTU5MjUxMTcyNTk0.GmFfgvc8ghQtEs2miH4nz9r2ZmuXey7VrGdBdkvi-MUg.7rftGC-XENXgEvVXpq5tRaEtM2njNTgS9o0VSPUwOOsg.PNG.wjddydwndi/DB.png?type=w773" width="100%">
<br>

<h3>[ Project 구조 ] </h3>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMjg0/MDAxNTU5MjU0OTExMDQx.p9Ioqg1y2e7G9Ai9mTxr4M-GdWZFlZkP4fW_DCsEo5Ig.ZcRgxdMFm6D2vZu6pE_24ab80wHh3HqAkgZD-kBbt64g.PNG.wjddydwndi/0.png?type=w773" width="100%">
<br>
<h3>①</h3>
<ul> - 모든 페이지를 관리하는 클래스.</ul>
<ul> - 로그인 정보를 모든 페이지로 주입.</ul>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMjk3/MDAxNTU5MjU0OTEzNjY3.ATdFif8BKazvy55O99qGDJFxclFtwBr6WRgQ0CnnZlog.W3hJ7AUZjca2BAXLr9TkOSAumjXYXbz1k_D7Yoc994gg.PNG.wjddydwndi/1.png?type=w773" width="100%">
<br>
<h3>① → ③</h3>
<ul> - Panel 배열에 각 클래스를 주입.</ul>
<ul> - 주입해놓은 배열을 이용하여, 원하는 페이지를 setVisible을 이용하여, 처리</ul>
<br>
<h3>① → ④</h3>
<ul> - Panel 배열에 각 클래스를 주입.</ul>
<ul> - 주입해놓은 배열을 이용하여, 원하는 페이지 반환.</ul>
<br>
<h3>②</h3>
<ul> - 로그인 후, Server측, DB에서 얻어온 정보를 모든 페이지에 주입.</ul>
<br>

<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMTIw/MDAxNTU5MjU0OTE1NjYx.7NMUzL_Q4Xwmf0eT8xf5y2lK5CGOOI3vrbD0kik_iMAg.yjAIuphQ35BIvEh-VPf60CKk5mFEInWpg7ZEc83qE_4g.PNG.wjddydwndi/2.png?type=w773" width="100%">
<br>
<h3> Socket 접속 및 서버 가동 클래스</h3>
<h3> ① </h3>
<ul> - 쓰레드를 이용하여, 서버 가동.  </ul>
<ul> - while문을 사용하여, 계속하여, 접속자를 받아들일 수 있는 환경 조성. </ul>
<ul> - 서버측의 Thread를 생성하므로써, 클라이언트 측의 송신을 계속 하여, 읽어들이도록 처리. </ul>
<ul> - List Collection을 이용하여, 접속자들의 퇴장 및 입장을 처리.</ul>
<br>
<h3> ② </h3>
<ul> - Server측의 ip,port 번호를 사용하여, 접속.</ul>
<ul> - 접속 시, Thread를 생성하여, 서버측 송신을 계속하여, 읽어들이도록 처리.</ul>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMjY2/MDAxNTU5MjU1MDExOTM4.baYC2ONg2wj49DNDfEE9jdE9J100D1qGrZNbG6FrOmog.Qxcbs70GIQDiL5ZOW3oBOLryIyUcpWvkc8ENj3cY2zgg.PNG.wjddydwndi/3.png?type=w773" width="100%">
<br>
<h3> ① </h3>
<ul> - Server , Client 각각 IO 를 생성하여, 정보를 송/수신 할 수 있는환경 조성</ul>
<ul> cf ) Stream을 종료 시, 접속이 끊김.</ul>
<br>
<h3> ② </h3>
<ul> - List Collection을 이용하여, Client들의 접속, 종료 처리.</ul>
<h3> ③ </h3>
<ul> - Server / Client 모두 Thread 클래스를 상속받은 클래스를 정의하여, 계속하여 수신할 수 있는 환경을 조성</ul>
<ul> L Listen()이라는 IO 메서드를 정의하여, 계속하여, 양측의 정보를 수신.</ul>
<ul> L Server 측, Thread는 접속자 수만큼 생성되어, List에 주입.</ul>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMjU3/MDAxNTU5MjU0OTE5MTEz.laj8olXa-g4A7pqVXVoG_PznTmD0Ekd90uP_W_niHGUg.X2p78rtspBM-r5rsvl33ZfWTrDtfl4VHY2DfgpDi14Yg.PNG.wjddydwndi/4.png?type=w773" width="100%">
<br>
<h3> Client 요청 처리 순서</h3>
<ul> - 해당페이지에서, 각각 요청에 맞는<p> Request클래스</p>로 요청에 필요한 값을 전달.</ul>
<ul> - 값을 전달받은 <p>Request클래스</p>는 <p>JSON형식으로 변환 후,</p> IO Stream을 이용하여, Server로 송신.</ul>
<ul> - Server측에서 값을 송신하면, 접속자마다 생성된 <p>ClientThread클래스<p>의 Listen() 메서드에서 읽어들임.</ul>
<ul> - Listen()메서드에서 읽어들인 String값은 <p>ClientDistributor</p>로 전달. </ul>
<ul> - <p>ClientDistributor</p>클래스 distribute()메서드에서 String형태를 다시 JSON형식으로 파싱하여, requestType을 구별하여, 해당 <p>Controller클래스</p>로 JSON객체를 전달.</ul>
<ul> - <p>해당 Controller클래스</p>에서 Server로부터 넘겨받은 값을 처리.</ul>
<br>

<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMyAg/MDAxNTU5MjU0OTIxMDc0.oOOyqDrpNrS1wyUNVc8vLhiG42hUlzQIEkHpcfp7-j0g.TPGd62VWBGEo6zEabBsBmd32de4W5xTZKaNxNSBOcJog.PNG.wjddydwndi/5.png?type=w773" width="100%">
<br>
<h3> Server 요청 처리 순서 </h3>
<ul> - Client의 <p>Request클래스</p>에서 송신한 요청을 <p>ServerThread클래스</p>의 Listen()메서드로 수신.</ul>
<ul> L <p>ServerThread클래스</p> 역시, ClientThread클래스와 마찬가지로, IO Stream을 이용하여 Listen()메서드를 정의.</ul>
<ul> - <p>Listen()메서드</p>에서 읽어들인 String형의 자료를 <p>ServerDispatcher클래스</p>로 전달.</ul>
<ul> - <p>ServerDispatcher클래스</p>의 distribute()메서드에서 넘겨받은 String형의 자료를 JSON형식으로 파싱.</ul>
<ul> - JSON형식의 자료의 requestType을 조사하여, 해당 요청사항에 맞는 <p>Controller클래스</p>로 JSON객체를 전달.</ul>
<ul> - <p>해당 Controller 클래스</p>에서 요청에 맞는 처리 후, 다시 Client측으로, 송신.</ul>
<br>
<h3> Example </h3>
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfNzgg/MDAxNTU5MjU0OTIyOTM2.CLqWr61xeQrbYYhiqEM8tKN1Yc3KgFry3pxZKdqd1zcg.KaW_85FmPMnZMik2qZ0mPhvqot-_mHxD0mVB-BmtV48g.PNG.wjddydwndi/6.png?type=w773" width="100%">



