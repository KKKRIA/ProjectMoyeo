package com.moyeo.service;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.moyeo.dao.NoticeDAO;
import com.moyeo.dto.Notice;
import com.moyeo.util.Pager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
	private final NoticeDAO noticeDAO;

	//공지사항 등록
	@Override
	public void insertNotice(Notice notice) {
		noticeDAO.insertNotice(notice);
	}

	//공지사항 조회(상세정보)
	@Override
	public Notice selectNoticeInfo(int noticeIdx) {
		return noticeDAO.selectNoticeInfo(noticeIdx);
	}

	//공지사항 수정
	@Override
	public void updateNotice(Notice notice) {
		noticeDAO.updateNotice(notice);
	}

	//공지사항 삭제
	@Override
	public void deleteNotice(int noticeIdx) {
		noticeDAO.deleteNotice(noticeIdx);
	}

	//공지사항 조회수
	@Override
	public void viewcntNotice(int noticeIdx) {
		noticeDAO.viewcntNotice(noticeIdx);

	}

	//공지사항 리스트 조회 + 페이징
	//매개변수로 요청페이지 번호를 전달받아 게시글 목록이 저장된 객체와 페이지 번호 관련 객체를 Map 객체의 엔트리로 추가하여 반환하는 메소드
	@Override
	public Map<String, Object> selectNoticeList(int pageNum, String keyword, String type) {
		int totalNotice=noticeDAO.selectNoticeCount(keyword, type);
		int pageSize= 10; //하나의 페이지에 출력될 게시글 개수 저장
		int blockSize=5;//하나의 블럭에 출력될 개수 저장

		Pager pager = new Pager(pageNum, totalNotice, pageSize, blockSize);

		Map<String, Object> pageMap=new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("keyword", keyword);
		pageMap.put("type", type);

		List<Notice> noticeList=noticeDAO.selectNoticeList(pageMap);

		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("pager", pager);
		resultMap.put("noticeList", noticeList);

		return resultMap;
	}
}
