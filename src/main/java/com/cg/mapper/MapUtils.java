//package com.cg.mapper;
//
//import com.cg.model.Product;
//import com.cg.model.Role;
//import com.cg.product.dto.ProductResult;
//import com.cg.role.dto.RoleResult;
//import org.modelmapper.ModelMapper;
//
//public class MapUtils {
//    private  static ModelMapper mapper = new ModelMapper();
//
//    public static RoleResult toRoleDTO(Role role){
//        return mapper.map(role, RoleResult.class);
//    }
//
//    public static ProductResult toProductDTO(Product product){
//        return mapper.map(product, ProductResult.class);
//    }
//    public static <T,D> D toDTO(T obj, Class<D> classD){
//        return mapper.map(obj, classD);
//    }
//
//}
