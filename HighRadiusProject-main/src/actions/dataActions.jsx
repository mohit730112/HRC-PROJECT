import { dataConstants } from "./actionConstants";
import axios from "../axiosInstance";
export const getData = (dataLength) => {
  return async (dispatch) => {
    try {
      dispatch({ type: dataConstants.GET_ALL_DATA_REQUEST });
      const res = await axios.get("/data", {
        params: {
          dataLength: `${dataLength}`,
        },
      });
      if (res.status === 200) {
        const data = res.data;
        console.log("data", data);
        if (data) {
          dispatch({
            type: dataConstants.GET_ALL_DATA_SUCCESS,
            payload: {
              data: data,
            },
          });
        }
      }
    } catch (error) {
      console.log(error);
      dispatch({
        type: dataConstants.GET_ALL_DATA_FAILURE,
        payload: {
          error: "Can't load data",
        },
      });
    }
  };
};

export const searchData = (searchedId) => {
  return async (dispatch) => {
    try {
      dispatch({ type: dataConstants.SEARCH_DATA_REQUEST });
      const res = await axios.get("/searchData", {
        params: {
          searchedId: `${searchedId}`,
        },
      });
      if (res.status === 200) {
        const data = res.data;
        console.log("data", data);
        dispatch({
          type: dataConstants.SEARCH_DATA_SUCCESS,
          payload: {
            searchedData: data,
          },
        });
      }
    } catch (error) {
      console.log(error);
      dispatch({
        type: dataConstants.SEARCH_DATA_FAILURE,
        payload: {
          error: "ERROR",
        },
      });
    }
  };
};

export const setSearch = () => {
  return async (dispatch) => {
    dispatch({ type: dataConstants.SET_SEARCH });
  };
};

export const setSnackbarMessage = (message) => {
  return async (dispatch) => {
    dispatch({
      type: dataConstants.SET_SNACKMESSAGE,
      payload: {
        snackMessage: message,
      },
    });
  };
};

export const modalOpen = (isOpen, modalTitle) => {
  return async (dispatch) => {
    dispatch({
      type: dataConstants.MODAL_OPEN,
      payload: {
        isOpen: isOpen,
        modalTitle: modalTitle,
      },
    });
  };
};

export const setDeletedArray = (deletedArray) => {
  return async (dispatch) => {
    dispatch({
      type: dataConstants.SET_DELETEARRAY,
      payload: {
        deletedId: deletedArray,
      },
    });
  };
};

export const addData = (newData) => {
  return async (dispatch) => {
    try {
      dispatch({ type: dataConstants.CREATE_DATA_REQUEST });
      const res = await axios.post(
        "/addData",
        {},
        {
          params: newData,
        }
      );
      console.log("RES DATA", res);
      if (res.status === 200) {
        const data = res.data;
        console.log("data", data);
        if (data) {
          dispatch({
            type: dataConstants.CREATE_DATA_SUCCESS,
            payload: {
              newData: newData,
            },
          });
        }
      }
    } catch (error) {
      console.log(error);
      dispatch({
        type: dataConstants.CREATE_DATA_FAILURE,
        payload: {
          error: "Can't add data",
        },
      });
    }
  };
};

export const deleteData = (idArray) => {
  return async (dispatch) => {
    let temp = {};

    for (let i = 0; i < idArray.length; i++) {
      const index = i + 1;
      temp[index] = idArray[i];
    }

    try {
      dispatch({ type: dataConstants.DELETE_DATA_REQUEST });
      const res = await axios.post(
        "/deleteData",
        {},
        {
          params: { ...temp, length: idArray.length },
        }
      );
      console.log("RES", res);
      if (res.status === 200) {
        // const data = res.data;
        console.log("delete");
        dispatch({
          type: dataConstants.DELETE_DATA_SUCCESS,
          payload: {
            deletedId: idArray,
          },
        });
      }
    } catch (error) {
      console.log(error);
      dispatch({
        type: dataConstants.DELETE_DATA_FAILURE,
        payload: {
          error: "Can't delete data",
        },
      });
    }
  };
};

export const editData = (idArray, amount, notes) => {
  console.log("Inside edit data");
  return async (dispatch) => {
    let temp = {};

    for (let i = 0; i < idArray.length; i++) {
      const index = i + 1;
      temp[index] = idArray[i];
    }

    try {
      dispatch({ type: dataConstants.EDIT_DATA_REQUEST });
      const res = await axios.post(
        "/edit",
        {},
        {
          params: {
            ...temp,
            length: idArray.length,
            amount: amount,
            notes: notes,
          },
        }
      );
      console.log("RES", res);
      if (res.status === 200) {
        // const data = res.data;
        console.log("delete");
        dispatch({
          type: dataConstants.EDIT_DATA_SUCCESS,
          payload: {
            editedId: idArray,
          },
        });
      }
    } catch (error) {
      console.log(error);
      dispatch({
        type: dataConstants.EDIT_DATA_FAILURE,
        payload: {
          error: "Can't edit data",
        },
      });
    }
  };
};
