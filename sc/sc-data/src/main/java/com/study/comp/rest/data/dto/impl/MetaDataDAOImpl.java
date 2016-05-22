package com.study.comp.rest.data.dto.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.study.comp.rest.data.dao.MetaDataDAO;
import com.study.comp.rest.data.dto.MetaData;

@Component
public class MetaDataDAOImpl implements MetaDataDAO {

	@Autowired
	private MongoOperations mongoTemplate;

	@Override
	public List<MetaData> getAllMetaData() {
		return mongoTemplate.findAll(MetaData.class);
	}

}
