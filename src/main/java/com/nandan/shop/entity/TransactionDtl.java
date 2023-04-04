package com.nandan.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="daily_transaction")
@NamedQuery(name="TransactionDtl.findAll", query="SELECT d FROM TransactionDtl d")
public class TransactionDtl implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="trans_id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long transactionId;

		@Column(name="amount_dip")
		private BigDecimal amountDip;
		
		@Column(name="amount_with")
		private BigDecimal amountwith;
		
		@Column(name="gold_dip")
		private BigDecimal goldDip;
		
		@Column(name="gold_with")
		private BigDecimal goldWith;
		
		@Column(name="silver_dip")
		private BigDecimal silverDip;
		
		@Column(name="silver_with")
		private BigDecimal silverWith;

		@Temporal(TemporalType.DATE)
		@Column(name="trans_date")
		private Date transDate;

		@Column(name="trans_description")
		private String transDescription;

		@Column(name="trans_stat")
		private int transStat;

		@Column(name="trans_type")
		private String transType;

		

		//bi-directional many-to-one association to UserDetail
		@ManyToOne
		@JoinColumn(name="trans_customer_id")
		@JsonIgnore
		private UserDetail userDetail;



		public Long getTransactionId() {
			return transactionId;
		}



		public void setTransactionId(Long transactionId) {
			this.transactionId = transactionId;
		}



		public BigDecimal getAmountDip() {
			return amountDip;
		}



		public void setAmountDip(BigDecimal amountDip) {
			this.amountDip = amountDip;
		}



		public BigDecimal getAmountwith() {
			return amountwith;
		}



		public void setAmountwith(BigDecimal amountwith) {
			this.amountwith = amountwith;
		}



		public BigDecimal getGoldDip() {
			return goldDip;
		}



		public void setGoldDip(BigDecimal goldDip) {
			this.goldDip = goldDip;
		}



		public BigDecimal getGoldWith() {
			return goldWith;
		}



		public void setGoldWith(BigDecimal goldWith) {
			this.goldWith = goldWith;
		}



		public BigDecimal getSilverDip() {
			return silverDip;
		}



		public void setSilverDip(BigDecimal silverDip) {
			this.silverDip = silverDip;
		}



		public BigDecimal getSilverWith() {
			return silverWith;
		}



		public void setSilverWith(BigDecimal silverWith) {
			this.silverWith = silverWith;
		}



		public Date getTransDate() {
			return transDate;
		}



		public void setTransDate(Date transDate) {
			this.transDate = transDate;
		}



		public String getTransDescription() {
			return transDescription;
		}



		public void setTransDescription(String transDescription) {
			this.transDescription = transDescription;
		}



		public int getTransStat() {
			return transStat;
		}



		public void setTransStat(int transStat) {
			this.transStat = transStat;
		}



		public String getTransType() {
			return transType;
		}



		public void setTransType(String transType) {
			this.transType = transType;
		}



		public UserDetail getUserDetail() {
			return userDetail;
		}



		public void setUserDetail(UserDetail userDetail) {
			this.userDetail = userDetail;
		}
		
		
}
