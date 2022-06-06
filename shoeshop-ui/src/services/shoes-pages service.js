import axiosService from '@/services/common'

class ShoesPagesService {
  getShoePages (params) {
    return axiosService.get('shoes/pages', { params })
  }
}
export default new ShoesPagesService()
