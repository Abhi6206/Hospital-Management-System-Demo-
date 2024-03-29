package com.java.hib;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.BooleanArraySerializer;

public class JsfPaginationBean {

	private List<insurance_Claim> cdList;
	private InsuranceImpl queryHelper;
	/**
	 * pagination stuff
	 */
	private int totalRows;
	static private int firstRow;
	private int rowsPerPage;
	private int totalPages;
	private int pageRange;
	private Integer[] pages;
	private int currentPage;
	/**
	 * Creates a new instance of JsfPaginationBean
	 */
	public JsfPaginationBean() {
		queryHelper = new InsuranceImpl();
		/**
		 * the below function should not be called in real world application
		 */
		// Set default values somehow (properties files?).
		rowsPerPage = 3; // Default rows per page (max amount of rows to be displayed at once).
		pageRange = 5; // Default page range (max amount of page links to be displayed at once).

	}
	public List<insurance_Claim> getInsuranceClaimList() throws ParseException {
		
		if (cdList == null) {
			loadInsuranceClaim();
		}
		return cdList;
	}
	
	//--------
	public void setCdList(List<insurance_Claim> cdList) {
		this.cdList = cdList;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageRange() {
		return pageRange;
	}
	public void setPageRange(int pageRange) {
		this.pageRange = pageRange;
	}
	public Integer[] getPages() {
		return pages;
	}
	public void setPages(Integer[] pages) {
		this.pages = pages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public InsuranceImpl getQueryHelper() {
		return queryHelper;
	}
	public void setQueryHelper(InsuranceImpl queryHelper) {  
		this.queryHelper = queryHelper;
	}
	private void loadInsuranceClaim() throws ParseException {

		cdList = queryHelper.getListOfInsuranceClaim(firstRow, rowsPerPage);
//		System.out.println("Insurance Count is  " +cdList);
		totalRows = queryHelper.countRows();
		System.out.println("Total Rows  " +totalRows);
		// Set currentPage, totalPages and pages.
		currentPage = (totalRows / rowsPerPage) - ((totalRows - firstRow) / rowsPerPage) + 1;
		totalPages = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);
		int pagesLength = Math.min(pageRange, totalPages);
		pages = new Integer[pagesLength];
		// firstPage must be greater than 0 and lesser than totalPages-pageLength.
		int firstPage = Math.min(Math.max(0, currentPage - (pageRange / 2)), totalPages - pagesLength);
		// Create pages (page numbers for page links).
		for (int i = 0; i < pagesLength; i++) {
			pages[i] = ++firstPage;
		}
	}	
	// Paging actions
	// -----------------------------------------------------------------------------
	public void pageFirst() {
		page(0);
	}
	public void pageNext() {
		page(firstRow + rowsPerPage);
	}
	public void pagePrevious() {
		page(firstRow - rowsPerPage);
	}
	public void pageLast() {
		page(totalRows - ((totalRows % rowsPerPage != 0) ? totalRows % rowsPerPage : rowsPerPage));
	}
	public void page(ActionEvent event) {
		page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
	}
	private void page(int firstRow) {
		this.firstRow = firstRow;
		try {
			loadInsuranceClaim();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 //------------------------------
	public void clear() throws IOException {
		firstRow=0;
		queryHelper.clear();
	}
	
}   

