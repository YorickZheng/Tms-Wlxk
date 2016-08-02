package com.wlxk.service.car.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.wlxk.controller.branch.vo.QueryBranchVo;
import com.wlxk.controller.car.vo.*;
import com.wlxk.controller.contract.vo.ContractView;
import com.wlxk.controller.contract.vo.QueryContractVo;
import com.wlxk.domain.car.Car;
import com.wlxk.domain.car.CarReview;
import com.wlxk.domain.car.Driver;
import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;
import com.wlxk.domain.contract.ContractOperationRecord;
import com.wlxk.domain.contract.ContractReview;
import com.wlxk.repository.car.CarRepository;
import com.wlxk.repository.car.specs.CarSpecs;
import com.wlxk.service.car.CarReviewService;
import com.wlxk.service.car.CarService;
import com.wlxk.service.car.DriverService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.exception.TmsException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.PageUtil;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class CarServiceImpl implements CarService {

    private final static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired(required = false)
    private CarRepository repository;
    @Autowired(required = false)
    private DriverService driverService;
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

            logger.info("3. 新增司机");
            List<Driver> driverList = vo.getDriverList();
            driverList.forEach(driver -> {
                driver.setCarId(car.getId());
            });
            driverService.save(driverList);

            logger.info("4. 新增审核");
            reviewService.save(CarReview.newDefaultInstance(car.getId()));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("新增失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增成功!");
    }

    private void checkAdd(AddCarVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getCar())) {
            throw new TmsDataValidationException("车辆对象不能为空!");
        }
        if (Objects.isNull(vo.getDriverList())) {
            throw new TmsDataValidationException("司机集合不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("操作说明不能为空!");
        }
    }

    @Override
    public Map review(ReviewCarVo vo) {
        try {
            logger.info("1. 数据校验");
            checkReview(vo);

            logger.info("2. " + (vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核"));
            CarReview review = reviewService.getOneByCarId(vo.getBusinessId());
            if (vo.getCommand().equals(CommonProperty.ReviewCommand.CANNEL_COMMAND)) {
                review.setStatus(CommonProperty.ReviewStatus.UNAUDITED);
            } else if (vo.getCommand().equals(CommonProperty.ReviewCommand.AUDITED_COMMAND)) {
                review.setStatus(CommonProperty.ReviewStatus.AUDITED);
            }
            review.setReviewById(vo.getOperationById());
            review.setReviewByName(vo.getOperationByName());
            review.setDescription(vo.getDescription());
            reviewService.save(review);

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核") + "成功!");
    }

    private void checkReview(ReviewCarVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getBusinessId())) {
            throw new TmsDataValidationException("业务ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("操作说明不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.ReviewCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("作废命令无效!");
        }
    }

    @Override
    public Map update(UpdateCarVo vo) {
        try {
            logger.info("1. 数据校验");
            checkUpdate(vo);

            logger.info("2. 更新车辆");
            Car car = vo.getCar();
            car.setModifyById(vo.getOperationById());
            car.setModifyByName(vo.getOperationByName());
            car.setModifyByDate(Calendar.getInstance().getTime());
            save(car);

            logger.info("3. 更新司机");
            if (!Objects.isNull(vo.getDriverList())) {
                logger.info("3-1. 删除司机");
                driverService.deleteByCarId(car.getId());

                logger.info("3-2. 新增司机");
                List<Driver> driverList = vo.getDriverList();
                driverList.forEach(driver -> {
                    driver.setCarId(car.getId());
                });
                driverService.save(driverList);
            } else {
                logger.info("无司机更新");
            }

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("更新失败!", e);
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
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("作废人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("说明不能为空!");
        }
    }

    @Override
    public Map disuse(DisuseCarVo vo) {
        try {
            logger.info("1. 数据校验");
            checkDisuse(vo);

            logger.info("2. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "车辆");
            String carId = vo.getBusinessId();
            Car car = repository.findOne(carId);
            if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                car.setDeleteFlag(Boolean.TRUE);
            } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                car.setDeleteFlag(Boolean.FALSE);
            }
            car.setModifyById(vo.getOperationById());
            car.setModifyByName(vo.getOperationByName());
            car.setModifyByDate(Calendar.getInstance().getTime());
            save(car);

            logger.info("3. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "司机");
            List<Driver> driverList = driverService.getListByCarId(carId);
            driverList.forEach(driver -> {
                if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                    driver.setDeleteFlag(Boolean.TRUE);
                } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                    driver.setDeleteFlag(Boolean.FALSE);
                }
                driver.setModifyById(vo.getOperationById());
                driver.setModifyByName(vo.getOperationByName());
                driver.setModifyByDate(Calendar.getInstance().getTime());
            });
            driverService.save(driverList);

            /*
            logger.info("3. 作废审核记录");
            List<CarReview> reviewList = reviewService.getListByCarId(carId);
            reviewList.forEach(carReview -> {
                carReview.setDeleteFlag(Boolean.TRUE);
            });
            reviewService.save(reviewList);*/

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == 1 ? "作废" : "取消作废") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == 1 ? "作废" : "取消作废") + "成功!");
    }

    private void checkDisuse(DisuseCarVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getBusinessId())) {
            throw new TmsDataValidationException("业务ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("作废人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("说明不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.DisuseCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("作废命令无效!");
        }
    }

    @Override
    public Page pageData(QueryCarVo vo) {
        Sort sort = new Sort(vo.getDirection(), vo.getSortList());
        PageRequest request = new PageRequest(vo.getPage(), vo.getSize(), sort);
        return repository.findAll(CarSpecs.CarPageSpecs(vo.getParams()), request);
    }

    @Override
    public Map pageView(QueryCarVo vo) {
        try {
            logger.info("1. 数据校验");
            getPageViewByDataValidation(vo);

            logger.info("2 查询主表");
            Page<Car> carPage = pageData(vo);

            logger.info("3. 查询子表");
            List<CarView> content = Lists.newArrayList();
            carPage.getContent().forEach(car -> {
                String carId = car.getId();
                List<Driver> driverList = driverService.getListByCarId(carId);

                content.add(CarView.newInstance(car, driverList));
            });

            logger.info("4. 组装数据返回");
            return ResultsUtil.getSuccessResultMap(PageUtil.newInstancePage(carPage, content));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            throw e;
        }
    }

    private void getPageViewByDataValidation(QueryCarVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getParams())) {
            throw new TmsDataValidationException("查询参数列表不能为空!");
        }
        if (Objects.isNull(vo.getPage())) {
            throw new TmsDataValidationException("起始页不能为空!");
        }
        if (Objects.isNull(vo.getSize())) {
            throw new TmsDataValidationException("每页数量不能为空!");
        }
        if (Objects.isNull(vo.getDirection())) {
            throw new TmsDataValidationException("排序方式不能为空!");
        }
        if (Objects.isNull(vo.getSortList())) {
            throw new TmsDataValidationException("排序集合不能为空!");
        }
    }

}
