# socket-project


프로젝트 주제	: Java Socket통신을 사용한 응용프로그램 만들기.<br>
<br>
프로젝트 설명 : 로그인 처리를 통하여, 유저마다 자신의 사진 및 글이 담긴 게시물을 게시.<br>
각 게시물마다 로그인 계정으로 댓글 작성 가능.<br>
<br>

개발 툴	: Eclipse<br>
<br>
개발 기술 : JAVA, Oracle, mybatis<br>
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
<ul> - 서버측의 Thread를 만듦으로써, 클라이언트 측의 송신을 계속 하여, 읽어들이도록 처리. </ul>
<ul> - List배열을 이용하여, 접속자들의 퇴장 및 입장을 처리.</ul>
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMjY2/MDAxNTU5MjU1MDExOTM4.baYC2ONg2wj49DNDfEE9jdE9J100D1qGrZNbG6FrOmog.Qxcbs70GIQDiL5ZOW3oBOLryIyUcpWvkc8ENj3cY2zgg.PNG.wjddydwndi/3.png?type=w773" width="100%">
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMjU3/MDAxNTU5MjU0OTE5MTEz.laj8olXa-g4A7pqVXVoG_PznTmD0Ekd90uP_W_niHGUg.X2p78rtspBM-r5rsvl33ZfWTrDtfl4VHY2DfgpDi14Yg.PNG.wjddydwndi/4.png?type=w773" width="100%">
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfMyAg/MDAxNTU5MjU0OTIxMDc0.oOOyqDrpNrS1wyUNVc8vLhiG42hUlzQIEkHpcfp7-j0g.TPGd62VWBGEo6zEabBsBmd32de4W5xTZKaNxNSBOcJog.PNG.wjddydwndi/5.png?type=w773" width="100%">
<img src="https://postfiles.pstatic.net/MjAxOTA1MzFfNzgg/MDAxNTU5MjU0OTIyOTM2.CLqWr61xeQrbYYhiqEM8tKN1Yc3KgFry3pxZKdqd1zcg.KaW_85FmPMnZMik2qZ0mPhvqot-_mHxD0mVB-BmtV48g.PNG.wjddydwndi/6.png?type=w773" width="100%">



