package com.revature.gradingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.util.ConnectionUtil;
import com.revature.gradingsystem.util.MessageConstant;
import com.revature.gradingsystem.model.StudentDetail;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.model.Subject;

public class StudentMarkDaoImpl {

	public void insertMarks(int regno, List<StudentMark> marks) throws DBException {

		for (StudentMark studentMark : marks) {

			Connection con = null;
			PreparedStatement pst = null;
			con = ConnectionUtil.getConnection();

			try {
				String sql = "insert into student_marks (reg_no, subject_code, marks) values (?,?,?)";
				pst = con.prepareStatement(sql);
				pst.setInt(1, regno);
				pst.setString(2, studentMark.getSubject().getCode());
				pst.setInt(3, studentMark.getMark());

				pst.executeUpdate();
			} catch (SQLException e) {
				throw new DBException(MessageConstant.UNABLE_TO_INSERT_MARK, e);
			} finally {
				ConnectionUtil.close(con, pst);
			}
		}
	}

	public List<StudentMark> findBySubjectCode(String subjectCode) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StudentMark> list = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select sd.reg_no, sd.student_name, sm.marks, s.subject_name from  student_details sd,"
					+ " student_marks sm, subject s where sd.reg_no = sm.reg_no and sm.subject_code = s.sub_code"
					+ " and subject_code = ? order by marks desc;";
			pst = con.prepareStatement(sql);
			pst.setString(1, subjectCode);

			rs = pst.executeQuery();
			list = new ArrayList<StudentMark>();
			while (rs.next()) {
				StudentMark sm = new StudentMark();

				StudentDetail sd = new StudentDetail();
				sd.setRegNo(rs.getInt("reg_no"));
				sd.setStudentName(rs.getString("student_name"));
				sm.setStudentDetail(sd);
				
				sm.setMark(rs.getInt("marks"));
				
				Subject sub = new Subject();
				sub.setName(rs.getString("subject_name"));
				sm.setSubject(sub);

				list.add(sm);
			}
		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_INSERT_MARK, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}

		return list;
	}

	public List<StudentMark> getMarksByRegNo(int regno) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StudentMark> list = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select subject_code, marks from student_marks where reg_no = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);

			rs = pst.executeQuery();
			list = new ArrayList<StudentMark>();
			while (rs.next()) {
				StudentMark mark = new StudentMark();
				Subject subject = new Subject();
				subject.setCode(rs.getString("subject_code"));
				mark.setSubject(subject);
				mark.setMark(rs.getInt("marks"));

				list.add(mark);
			}
		} catch (SQLException e) {
			throw new DBException(MessageConstant.UNABLE_TO_GET_RECORDS, e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return list;
	}
}
