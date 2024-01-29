package business;

import core.Helper;
import dao.CarDao;
import entity.Car;

import java.util.ArrayList;

public class CarManager {
    private final CarDao carDao ;


    public CarManager() {
        this.carDao = new CarDao ();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Car> cars) {
        ArrayList<Object[]> carObjList = new ArrayList<>();
        for (Car obj : cars) {
            Object[] rowObject = new Object[size];

            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getModel().getBrand().getName();
            rowObject[i++] = obj.getModel().getName();
            rowObject[i++] = obj.getPlate();
            rowObject[i++] = obj.getColor();
            rowObject[i++] = obj.getKm();
            rowObject[i++] = obj.getModel().getYear();
            rowObject[i++] = obj.getModel().getType();
            rowObject[i++] = obj.getModel().getFuel();
            rowObject[i++] = obj.getModel().getGear();

            carObjList.add(rowObject);
        }
        return carObjList;
    }

    public ArrayList<Car> findAll() {
        return this.carDao.findAll();
    }

    public boolean save(Car car) {
        if (car.getId() != 0) {
            Helper.showMsg("error");
            return false;
        }
        return this.carDao.save(car);
    }

    public Car getById(int id) {
        return this.carDao.getById(id);
    }

    public boolean update(Car car) {
        if (this.getById(car.getId()) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.carDao.update(car);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.carDao.delete(id);
    }

    public ArrayList<Car> getByListCarId(int carId) {
        return this.carDao.getByListModelId(carId);
    }

    public ArrayList<Car> searchForTable(int modelId, Car.Color color) {
        String select = "SELECT * FROM public.car";
        ArrayList<String> whereList = new ArrayList<>();

        if (modelId != 0) {
            whereList.add("car_model_id =" + modelId);
        }

        if (color != null) {
            whereList.add("car_color ='" + color.toString() + "'");
        }


        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        return this.carDao.selectByQuery(query);
    }
}

