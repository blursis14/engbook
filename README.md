
ENGBOOK은 영어 문장 암기에 도움을 주는 웹어플리케이션입니다.

문장을 통으로 외우는 것은 외국어를 공부하는 데 있어서 가장 효과적인 방법 중 하나입니다.

ENGBOOK은 단어장을 만들어 암기하듯, 외우면 유익할 문장을 등록해 암기하고 그 문장을 다른 사람도 퍼갈 수 있으면 좋을 것이란 생각에서 출발했습니다.

자신이 외우고 싶은 영어문장과 그 뜻, 메모를 등록할 수 있습니다.

등록한 문장은 메인화면에 공개되고, 다른 회원들이 그것을 본인의 북마크에 추가할 수 있습니다.

북마크의 폴더 기능을 활용할 수 있습니다. 폴더는 삭제/폴더명 수정이 가능합니다.

북마크의 삭제버튼을 클릭하면 문장이 아예 삭제되고, 외웠어요 버튼을 클릭하면 그것이 자신의 북마크에서만 사라지고 다른사람은 여전히 볼 수 있습니다.

외워야 할 단어나 숙어, 문법이 있다면 그것을 검색하고 북마크에 추가해 예문을 통으로 외워보세요. 단어만 보며 외우는 것보다 그 뜻과 뉘앙스를 더 빨리 익힐 수 있습니다.

사이트 주소는http://ec2-3-34-59-204.ap-northeast-2.compute.amazonaws.com/main 입니다.

개발 정보

IDE로 이클립스를 사용했습니다.

백엔드를 자바와 스프링 부트,JPA와 MYSQL를 이용해 구축했습니다.

뷰에서 JSP와 자바스크립트,제이쿼리,AJAX,HTML5,부트스트랩을 혼용했습니다.

RestController와 Controller를 사용해 AJAX가 필요한 부분은 RestController를 사용하고, 뷰의 전환이 필요한 부분은 Controller를 사용했습니다.

검색엔진으로 Hibernate search를 사용했습니다.

서버 부하 방지와 속도향상을 위해 데이터를 한번에 불러오는 것이 아닌, 스크롤이 바닥에 닿을 때마다 AJAX를 호출해 새로운 데이터를 받아오는 방식을 사용했습니다.

AWS의 우분투 ec2를 통해 배포했습니다.

github 데스크탑 어플리케이션으로 형상관리를 했습니다.

기능 추가와 유지보수를 계속할 예정입니다.

참고서적은 명품 자바 프로그래밍(황기태 저), 최범균의 JSP2.3 웹프로그래밍, 초보 웹 개발자를 위한 스프링5 프로그래밍 입문(최범균 저)입니다.
