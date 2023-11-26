package com.companyname.mybookstore.services;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class MyBookStoreService {

    public static final String module = MyBookStoreService.class.getName();

    public static Map<String, Object> createNewBook(DispatchContext dctx, Map<String, ? extends Object> context) {
            Map<String, Object> result = ServiceUtil.returnSuccess();
            Delegator delegator = dctx.getDelegator();
            try {
                GenericValue bookEntity = delegator.makeValue("book");
                // Auto generating next sequence of bookId primary key
                bookEntity.setNextSeqId();
                // Setting up all non primary key field values from context map
                bookEntity.setNonPKFields(context);
                // Creating record in database for book entity for prepared value
                bookEntity = delegator.create(bookEntity);
                result.put("bookId", bookEntity.getString("bookId"));
                Debug.log("==========This is my first Java Service implementation in Apache OFBiz. book record created successfully with bookId: "+bookEntity.getString("bookId"));
            } catch (GenericEntityException e) {
                Debug.logError(e, module);
                return ServiceUtil.returnError("Error in creating record in book entity ........" +module);
            }
            return result;
        }

    }