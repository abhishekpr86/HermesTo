package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.VendorDao;
import com.capgemini.onboarding.model.Vendor;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorDao vendorDao;

	@Override
	@Transactional
	public List<Vendor> listVendor(int countryId, boolean isBundleEMReq) {
		return vendorDao.listVendor(countryId, isBundleEMReq);
	}

	@Override
	@Transactional
	public Vendor getVendorById(int id) {
		return vendorDao.getVendorById(id);
	}

}
