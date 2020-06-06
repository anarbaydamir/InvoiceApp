package com.digitallife.invoice.service.inter;

import com.digitallife.invoice.entity.ClientVendorAmount;

public interface AmountServiceInter<T,U> {
    public ClientVendorAmount getClientAmount(T t,U u);

    public ClientVendorAmount getVendorAmount(T t,U u);

    public ClientVendorAmount getExpectedClientAmount(T t,U u);

    public ClientVendorAmount getExpectedVendorAmount(T t, U u);
}
