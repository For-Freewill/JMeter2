package com.test;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class CustomJMeter extends AbstractJavaSamplerClient{
	  
	@Override
	public void setupTest(JavaSamplerContext args) {

	}
	
	@Override
	public Arguments getDefaultParameters() {
		    Arguments params = new Arguments();  
	        params.addArgument("Url", "https://10.30.2.151/phoenix/login-html.jsf"); // 定义一个参数，显示到Jmeter的参数列表中，第一个参数为参数默认的显示名称，第二个参数为默认值 
	        params.addArgument("Org", "super");
	        params.addArgument("Name", "wulei");
	        return params;  
	}

	@Override
	public SampleResult runTest(JavaSamplerContext context) {
		SampleResult sr = new SampleResult();
		try {	
			sr.sampleStart();

			String arg1 = context.getParameter("Url"); 
			String arg2 = context.getParameter("Org"); 
			AotuTest.runTest(arg1, arg2);
			sr.setSampleLabel("结果");
	        sr.setResponseData("返回结果："+AotuTest.runTest(arg1, arg2), "UTF-8");
			sr.setDataType(SampleResult.TEXT);
			sr.setSuccessful(true);
		} catch (Exception e) {
			sr.setSuccessful(false);
			e.printStackTrace();
		}finally {
            sr.sampleEnd();// jmeter 结束统计响应时间标记
        }
		return sr;
	}

	@Override
	public void teardownTest(JavaSamplerContext context) {
		// TODO Auto-generated method stub
		super.teardownTest(context);
	}
}
  