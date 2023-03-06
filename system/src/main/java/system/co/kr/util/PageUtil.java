package system.co.kr.util;


public class PageUtil {
	public int nowPage;		// ���� �������� ����� ����
	public int totalCount;	// �� ������ ������ ����� ����
	
	public int listCount;	// �� ȭ�鿡 ������ ����� ���� - 10��
	public int pageGroup;	// �� ȭ�鿡 ������ ������ �� - 5������
	
	public int totalPage;	// �� ������ ��
	public int startPage;	// ���� ������
	public int endPage;		// �� ������
	
	
	public int start;		//	�˻� ���� ��ȣ
	public int end;			//	�˻� �� ��ȣ
	
	
	
	//	�������� �˻� ���� ��ȣ�� �� ��ȣ�� ����ؼ� ��ȯ�ϴ� �Լ�
	public int getStartNum() {
		
		return (nowPage-1) * listCount +1;
	}
	
	public int getEndNum() {
		
		return nowPage * listCount;
	}
	
	// �������� �� Ŭ������ new ��Ű�鼭 �ʼ� ������ �˷��ֱ�� ����.
	public PageUtil(int nowPage, int totalCount, int listCount) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.listCount = listCount;
		// �̵��� �� �κ��� �����ؼ� ����� Ȯ���ϵ��� ����.
		/*listCount = 4;*/
		pageGroup = 5;
		
		// �Ʒ��ʿ� ���� �Լ��� �ϳ��� ȣ���ؼ� ������ 3���� ������ ��������.
		calcTotalPage();
		calcStartPage();
		calcEndPage();
	}
	// �� ���������� ����� �Լ�
	private void calcTotalPage() {
		// �� ȭ�鿡 10���� ����ϱ�� �����Ƿ� �� ������ 100���̸� 10�������� �ʿ��ϰ� 
		// �� ������ 101���̸� 11�������� �ʿ��ϴ�.
		totalPage = (totalCount % listCount) == 0 ? (totalCount / listCount) : (totalCount / listCount) + 1;
	}
	
	// ���� �������� ����� �Լ�
	private void calcStartPage() {
		// ����
		// ���� �������� �� ��° �׷������� �˾Ƴ���,
		int tempGroup = (nowPage - 1) / pageGroup + 1;
		// �� �׷��� ���� �������� ���Ѵ�.
		startPage = (tempGroup - 1) * pageGroup + 1;
	}
	
	// ������ �������� ����� �Լ�
	private void calcEndPage() {
		// ���� ������ + pageGroup - 1�� �ϸ� �ȴ�.
		endPage = startPage + pageGroup - 1;
		// �ٸ� ������ �������� �� ����� �ƴ� �� �ִ�.
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	
	// �� ������ view���� JSTL�� �̿��ؼ� ����ؾ� �ϹǷ� 
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getPageGroup() {
		return pageGroup;
	}
	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}	
}

