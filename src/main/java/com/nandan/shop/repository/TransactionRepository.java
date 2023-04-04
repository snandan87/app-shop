package com.nandan.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nandan.shop.entity.TransactionDtl;
import com.nandan.shop.entity.UserDetail;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionDtl, Long> {

	@Query("SELECT d FROM TransactionDtl d where d.userDetail = ?1")
	public List<TransactionDtl> findAllTransactionOfUser(UserDetail userDetail);
}