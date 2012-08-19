/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.lucene.indexer;

/**
 *
 * @author arun
 */

import java.io.File;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.HasAttributeFilter;

public class Extractor {
    String getSectionTitle(File f){
        
        HasAttributeFilter filter0 = new HasAttributeFilter ();
        filter0.setAttributeName ("name");
        filter0.setAttributeValue ("Section-title");
        NodeFilter[] array0 = new NodeFilter[1];
        array0[0] = filter0;
        FilterBean bean = new FilterBean ();
        bean.setFilters (array0);
        bean.setURL (f.toString());
        String str=bean.getNodes ().toHtml ();
        str=str.substring(str.indexOf("content=\"")+"content=\"".length());
        str=str.replace("\">", "");
        return str;
    }
    
    String getSectionSummary(File f){
        HasAttributeFilter filter0 = new HasAttributeFilter ();
        filter0.setAttributeName ("class");
        filter0.setAttributeValue ("summary");
        NodeFilter[] array0 = new NodeFilter[1];
        array0[0] = filter0;
        FilterBean bean = new FilterBean ();
        bean.setFilters (array0);
        bean.setURL (f.toString());
        String str=bean.getNodes ().toHtml ();
        if(str.length()==0){
                 filter0.setAttributeName ("name");
                    filter0.setAttributeValue ("description");
       
        array0[0] = filter0;
        bean.setFilters (array0);
       
         str=bean.getNodes ().toHtml ();
         if(str.length()!=0){
        str=str.substring(str.indexOf("content=\"")+"content=\"".length());
        str=str.replace("\">", "");}
        return str;
             }
        else{
        str=str.replaceAll("<[a-zA-Z]*\\s*[a-zA-Z]*=\"[a-zA-Z]*\">","");
        str=str.replaceAll("</\\s*[a-zA-Z]*>","");
        return str;
    }
    
    }
}
