package com.lchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * �ֲ�ʽ�������ĵ�ConfigServer
 * @author pc
 *  1.@EnableConfigServerע�⿪��config server
 *  2.������eurekaע�����ģ�������AppConfigServer
 *  3.��ΰ������ļ���ŵ�git��:
      ��git�ϴ��������ļ����ƹ淶����������-����.properties/yml/yaml  
      ��member-dev.yml��ʾ��Ա���񿪷����������ã�member-prod.yml��ʾ��Ա������������������  
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer  
public class AppConfigServer {
	public static void main(String[] args) {
		SpringApplication.run(AppConfigServer.class, args);
	}

}
