import axiosService from '@/services/common'

class ShoesPagesService {
  getShoePages (params) {
    return axiosService.get('shoes/pages', { params })
  }

  getShoeItem (params) {
    return axiosService.get('shoes/findShoe', { params })
  }
}

export default new ShoesPagesService()
