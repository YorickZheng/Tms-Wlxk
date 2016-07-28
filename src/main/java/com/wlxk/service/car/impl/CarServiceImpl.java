package com.wlxk.service.car.impl;

import com.google.common.base.Strings;
import com.wlxk.controller.car.vo.AddCarVo;
import com.wlxk.controller.car.vo.DisuseCarVo;
import com.wlxk.controller.car.vo.ReviewCarVo;
import com.wlxk.controller.car.vo.UpdateCarVo;
import com.wlxk.domain.car.Car;
import com.wlxk.domain.car.CarReview;
import com.wlxk.repository.car.CarRepository;
import com.wlxk.service.car.CarReviewService;
import com.wlxk.service.car.CarService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.exception.TmsException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by malin on 2016/7/26.
 */
public class CarServiceImpl implements CarService {

    private final static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired(required = false)
    private CarRepository repository;
    @Autowired(required = false)
    private CarReviewService reviewService;

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }

    @Override
    public Map add(AddCarVo vo) {
        try {
            logger.info("1. 数据校验");
            checkAdd(vo);

            logger.info("2. 新增车辆");
            Car car = save(vo.getCar());

            logger.info("3. 新增审核");
            reviewService.save(CarReview.newDefaultInstance(car.getId()));

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增成功!");
    }

    private void checkAdd(AddCarVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
    }

    @Override
    public Map review(ReviewCarVo vo) {
        try {
            logger.info("1. 数据校验");
            checkReview(vo);

            logger.info("2. 审核数据");
            CarReview review = reviewService.getOneByCarId(vo.getCarId());
            review.setReviewById(vo.getReviewById());
            review.setReviewByPerson(vo.getReviewByName());
            review.setDescription(vo.getDescription());
            review.setStatus(CommonProperty.ReviewStatus.AUDITED);
            reviewService.save(review);
            
        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("审核成功!");
    }

    private void checkReview(ReviewCarVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getCarId())) {
            throw new TmsDataValidationException("车辆编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getReviewById())) {
            throw new TmsDataValidationException("审核人编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getReviewByName())) {
            throw new TmsDataValidationException("审核人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("审核说明不能为空!");
        }
    }

    @Override
    public Map update(UpdateCarVo vo) {
        try {
            logger.info("1. 数据校验");
            checkUpdate(vo);

            logger.info("2. 更新数据");
            save(vo.getCar());

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("更新成功!");
    }

    private void checkUpdate(UpdateCarVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getCar())) {
            throw new TmsDataValidationException("车辆对象不能为空!");
        }
    }

    @Override
    public Map disuse(DisuseCarVo vo) {
        try {
            logger.info("1. 数据校验");
            checkDisuse(vo);

            logger.info("2. 作废车辆");
            String carId = vo.getCarId();
            Car car = repository.findOne(carId);
            car.setDeleteFlag(Boolean.TRUE);
            save(car);

            logger.info("3. 作废审核记录");
            List<CarReview> reviewList = reviewService.getListByCarId(carId);
            reviewList.forEach(carReview -> {
                carReview.setDeleteFlag(Boolean.TRUE);
            });
            reviewService.save(reviewList);

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("作废成功!");
    }

    private void checkDisuse(DisuseCarVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getCarId())) {
            throw new TmsDataValidationException("车辆编号不能为空!");
        }
    }
}
