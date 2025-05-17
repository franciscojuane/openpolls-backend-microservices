package com.francisco.users_service.v1.model;

import com.francisco.users_service.v1.model.common.AbstractModel;
import com.francisco.users_service.v1.model.common.Constants;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.TABLE_PREFIX + "PERMISSION")
public class Permission extends AbstractModel {

    private String name;
    
}