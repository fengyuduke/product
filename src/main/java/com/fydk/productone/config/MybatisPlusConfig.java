package com.fydk.productone.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;

@Configuration
public class MybatisPlusConfig {

    @Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");

		return page;
	}

   @Bean
   public static MapperScannerConfigurer mapperScannerConfigurer() {
       MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
       scannerConfigurer.setBasePackage("com.fydk.*.mapper;com.fydk.*.*.mapper;com.fydk.*.*.*.mapper");
       return scannerConfigurer;
   }
   
   // 开启SQL分析插件
   @Bean
   public PerformanceInterceptor performanceInterceptor() {
       return new PerformanceInterceptor();
   }
}