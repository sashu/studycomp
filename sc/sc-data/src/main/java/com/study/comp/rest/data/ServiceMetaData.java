package com.study.comp.rest.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.comp.rest.data.dao.MetaDataDAO;
import com.study.comp.rest.data.dto.MetaData;

@Service
public class ServiceMetaData {

	public static Map<String, Object> METADATA = new HashMap<String, Object>();

	@Autowired
	private MetaDataDAO metaDataDAO;

	@PostConstruct
	public void init() {
		List<MetaData> metaData = metaDataDAO.getAllMetaData();
		System.out.println(metaData.size());
	}

}
